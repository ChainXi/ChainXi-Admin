package com.chainxi.system.config.cache;

import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.constants.system.DefaultValueConstants;
import com.chainxi.system.mapper.cache.SysCacheInfoMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.*;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfiguration {

    @Bean
    public SimpleModule simpleKeyDeserializer() {
        return new SimpleModule().addDeserializer(SimpleKey.class, SimpleKeyDeserializer.INSTANCE);
    }

    @Bean
    @Order(0)
    public CacheBuilder cacheBuilderL1(RedisConnectionFactory connectionFactory,
            RedisCacheConfiguration redisCacheConfiguration) {
        return new CacheBuilderRedisImpl(new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory), redisCacheConfiguration), redisCacheConfiguration);
    }

    @Bean
    @Order(1)
    public CacheBuilder cacheBuilderL2() {
        return new CacheBuilderCaffeineImpl();
    }

    @Bean
    public CacheConfigSynchronizer cacheConfigSynchronizer(SysCacheInfoMapper mapper,
            List<CacheBuilder> cacheBuilders) {
        return new CacheConfigSynchronizerImpl(mapper, cacheBuilders);
    }

    @Bean
    public CacheDataChangedBroadcast cacheDataChangedBroadcast(RedisTemplate<Object, Object> redisTemplate) {
        return new CacheDataChangedBroadcastRedisChanelImpl(redisTemplate);
    }

    @Bean
    public MultiLevelCacheManager multiCacheManager(CacheConfigSynchronizer configSynchronizer,
            CacheDataChangedBroadcast dataSynchronizer) {
        Map<String, List<CacheExpireTime>> config = new LinkedHashMap<>();
        config.put(CacheKeyConstants.ENCRYPT,
                Collections.singletonList(new CacheExpireTime(StorageCache.REDIS,
                        DefaultValueConstants.CACHE_ENCRYPT_EXPIRE)));
        config.put(CacheKeyConstants.DICT_LABEL,
                Arrays.asList(new CacheExpireTime(StorageCache.CAFFEINE,
                        DefaultValueConstants.CACHE_DICT_LABEL_LOCAL_EXPIRE),
                        new CacheExpireTime(StorageCache.REDIS,
                                DefaultValueConstants.CACHE_DICT_LABEL_REMOTE_EXPIRE)));
        config.put(CacheKeyConstants.DICT_VALUE,
                Arrays.asList(new CacheExpireTime(StorageCache.CAFFEINE,
                        DefaultValueConstants.CACHE_DICT_VALUE_LOCAL_EXPIRE),
                        new CacheExpireTime(StorageCache.REDIS,
                                DefaultValueConstants.CACHE_DICT_VALUE_REMOTE_EXPIRE)));
        config.put(CacheKeyConstants.AUTH_USER,
                Arrays.asList(new CacheExpireTime(StorageCache.CAFFEINE,
                        DefaultValueConstants.CACHE_AUTH_USER_LOCAL_EXPIRE),
                        new CacheExpireTime(StorageCache.REDIS,
                                DefaultValueConstants.CACHE_AUTH_USER_REMOTE_EXPIRE)));
        config.put(CacheKeyConstants.CAPTCHA_CODE_KEY,
                Arrays.asList(new CacheExpireTime(StorageCache.CAFFEINE,
                        DefaultValueConstants.CACHE_CAPTCHA_CODE_LOCAL_EXPIRE),
                        new CacheExpireTime(StorageCache.REDIS,
                                DefaultValueConstants.CACHE_CAPTCHA_CODE_REMOTE_EXPIRE)));
        config.put(CacheKeyConstants.DATA_PERMISSION_TABLE_KEY,
                Arrays.asList(new CacheExpireTime(StorageCache.CAFFEINE,
                        DefaultValueConstants.CACHE_DATA_PERMISSION_TABLE_LOCAL_EXPIRE),
                        new CacheExpireTime(StorageCache.REDIS,
                                DefaultValueConstants.CACHE_DATA_PERMISSION_TABLE_REMOTE_EXPIRE)));

        return new MultiLevelCacheManager(configSynchronizer, dataSynchronizer, config);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    @Bean
    public CacheDataChangedReceiver cacheDataChangedReceiver(RedisTemplate<Object, Object> redisTemplate, RedisMessageListenerContainer listenerContainer, MultiLevelCacheManager cacheManager) {
        return new CacheDataChangedReceiverRedisChannelImpl(redisTemplate, listenerContainer,
                cacheManager);
    }
}