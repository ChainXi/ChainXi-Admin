参考:

[# SpringBoot Cache 实现二级缓存](https://juejin.cn/post/7245838232012931132)

[# Spring基于redis动态缓存过期时间](https://juejin.cn/post/7213297003869208633)

需求:系统维度配置增加common内存缓存,需要可动态配置过期时间,此处忽略业务代码

1.与RedisCache远端序列化存储不同,Caffeine实现上为弱引用直接存入容器,因此不必与RedisCache流程一致去做序列化

2.RedisCache存在很多闭包属性,实现上覆盖原有RedisCacheManager代码,仅打开createRedisCache方法,依赖升级时需注意检查变更记录


```
package com.chainxi.system.config.cache;

import org.springframework.cache.Cache;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 22:22
 * @Desc :
 */
public interface CacheBuilder<T extends Cache> {
    T build(@Nonnull String name, @Nonnull Integer expireTime);
}


package com.chainxi.system.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCache;

import jakarta.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

/**
 * @Author : ChainXi
 * @Date : 2024/5/8 3:48
 * @Desc :
 */
public class CacheBuilderCaffeineImpl implements CacheBuilder<CaffeineCache> {
    @Override
    public CaffeineCache build(@Nonnull String name, @Nonnull Integer expireTime) {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
        if (expireTime > 0) {
            caffeine.expireAfterWrite(expireTime, TimeUnit.MILLISECONDS);
        }
        return new CaffeineCache(name, caffeine.build());
    }
}
package com.chainxi.system.config.cache;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.time.Duration;

/**
 * @Author : ChainXi
 * @Date : 2024/5/8 3:29
 * @Desc :
 */
@RequiredArgsConstructor
public class CacheBuilderRedisImpl implements CacheBuilder<RedisCache> {
    private final RedisCacheManager redisCacheManager;
    private final RedisCacheConfiguration defaultCacheConfig;

    @Override
    public RedisCache build(@Nonnull String name, @Nonnull Integer expireTime) {
        return redisCacheManager.createRedisCache(name,expireTime > 0 ?
                defaultCacheConfig.entryTtl(Duration.ofMillis(expireTime)) :
                defaultCacheConfig);
    }
}


package com.chainxi.system.config.cache;

import org.springframework.cache.Cache;

import jakarta.annotation.Nonnull;
import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 21:59
 * @Desc :
 */
public interface CacheConfigSynchronizer {
    void sync(@Nonnull String name, @Nonnull List<Cache> list, List<Integer> defaultConfig);
}


package com.chainxi.system.config.cache;

import com.chainxi.system.bo.SysCacheInfoBo;
import com.chainxi.system.convert.cache.SysCacheInfoConvert;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.mapper.cache.SysCacheInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 22:03
 * @Desc :
 */
@Slf4j
@RequiredArgsConstructor
public class CacheConfigSynchronizerImpl implements CacheConfigSynchronizer {
    private final SysCacheInfoMapper sysCacheInfoMapper;
    private final List<CacheBuilder> builders;

    public SysCacheInfoBo getInfo(String name) {
        SysCacheInfoDo sysCacheInfoDo = sysCacheInfoMapper.selectOneByName(name);
        return sysCacheInfoDo != null ?
                SysCacheInfoConvert.INSTANCE.convert2CacheInfoBo(sysCacheInfoDo) :
                null;
    }

    @Override
    public void sync(@Nonnull String name,
            @Nonnull List<Cache> currentCaches,
            List<Integer> defaultConfig) {
        SysCacheInfoBo info = getInfo(name);
        List<Integer> expireTimes;
        if (info != null) {
            expireTimes = info.getExpireTimes();
        } else {
            expireTimes = defaultConfig;
        }
        ArrayList<Cache> caches = new ArrayList<>(currentCaches);
        currentCaches.clear();
        caches.forEach(Cache::clear);
        //TBD 对业务上仅非持久化存储数据有影响,是否清除旧的远端缓存?建议另外提供主动清除api
        for (int i = 0; i < expireTimes.size(); i++) {
            Integer expireTime = expireTimes.get(i);
            if (expireTime != -1) {
                currentCaches.add(builders.get(i).build(name, expireTime));
            }
        }

        /*
        if (currentCaches.size() >= expireTimes.size() + 1) {
            List<Cache> caches =
                    currentCaches.subList(expireTimes.size() + 1, currentCaches.size() + 1);
            caches.forEach(Cache::clear);
            caches.clear();
        }
        for (int i = 0; i < expireTimes.size(); i++) {
            Integer expireTime = expireTimes.get(i);
            if (i == currentCaches.size()) {
                currentCaches.add(builders.get(i).build(name, expireTime));
            } else if (i < currentCaches.size()) {
                //TBD 对业务上仅非持久化存储数据有影响,是否清除旧的远端缓存?建议另外提供主动清除api
                currentCaches.set(i, builders.get(i).build(name, expireTime));
            }
        }
        */
        log.debug("cacheName:{},currentCaches:{},expireTimes:{}", name, currentCaches, expireTimes);
    }
}
package com.chainxi.system.config.cache;

import com.chainxi.system.constants.system.CacheKeyConstants;
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
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2024/5/15 1:18
 * @Desc :
 */
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
    public CacheBuilder cacheBuilderL1(RedisCacheManager redisCacheManager,
            RedisCacheConfiguration redisCacheConfiguration) {
        return new CacheBuilderRedisImpl(redisCacheManager, redisCacheConfiguration);
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
        LinkedHashMap<String, List<Integer>> initialCacheConfiguration = new LinkedHashMap<>();
        initialCacheConfiguration.put(CacheKeyConstants.ENCRYPT,
                Collections.singletonList(3600000));
        initialCacheConfiguration.put(CacheKeyConstants.DICT_LABEL, Arrays.asList(-1, 60000));
        initialCacheConfiguration.put(CacheKeyConstants.DICT_VALUE, Arrays.asList(-1, 60000));
        initialCacheConfiguration.put(CacheKeyConstants.CAPTCHA_CODE_KEY, Arrays.asList(60000, -1));
        initialCacheConfiguration.put(CacheKeyConstants.DATA_PERMISSION_TABLE_KEY,
                Arrays.asList(-1, 600000));

        return new MultiLevelCacheManager(configSynchronizer, dataSynchronizer,
                initialCacheConfiguration);
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
package com.chainxi.system.config.cache;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/5/9 0:37
 * @Desc :
 */
public interface CacheDataChangedBroadcast {
    void syncData(@Nonnull String cacheName, Object key);
}
package com.chainxi.system.config.cache;

import com.chainxi.system.constants.system.RedisMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/5/9 2:14
 * @Desc :
 */
@RequiredArgsConstructor
public class CacheDataChangedBroadcastRedisChanelImpl implements CacheDataChangedBroadcast {
    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void syncData(@Nonnull String cacheName, Object key) {
        redisTemplate.convertAndSend(RedisMessageConstants.SYNC_TOPIC,
                new CacheRefreshMsg(CacheRefreshMsg.Flag.DATA,
                        cacheName,
                        key,
                        RedisMessageConstants.DEVICE_ID));
    }
}
package com.chainxi.system.config.cache;

/**
 * @Author : ChainXi
 * @Date : 2024/5/12 0:14
 * @Desc :
 */
public interface CacheDataChangedReceiver {
    void onGlobalDataChanged(String name, Object key);

    void onConfigChanged(String name);
}
package com.chainxi.system.config.cache;

import com.chainxi.system.constants.system.RedisMessageConstants;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Optional;

/**
 * @Author : ChainXi
 * @Date : 2024/5/10 6:01
 * @Desc :
 */
@Slf4j
@RequiredArgsConstructor
public class CacheDataChangedReceiverRedisChannelImpl implements MessageListener,
        CacheDataChangedReceiver, ApplicationListener {
    private final RedisTemplate<Object, Object> redisTemplate;
    private final RedisMessageListenerContainer listenerContainer;
    private final MultiLevelCacheManager cacheManager;

    @Override
    public void onGlobalDataChanged(String name, Object key) {
        Optional
                .ofNullable(cacheManager.getCache(name))
                .ifPresent(cache -> cache.evictLocal(key));
    }

    @Override
    public void onConfigChanged(String name) {
        Optional
                .ofNullable(cacheManager.getCache(name))
                .ifPresent(MultiLevelCache::syncConfig);
    }

    @Override
    public void onMessage(@Nonnull Message message, @Nonnull byte[] pattern) {
        CacheRefreshMsg refreshMsg = (CacheRefreshMsg) redisTemplate
                .getValueSerializer()
                .deserialize(message.getBody());
        if (refreshMsg == null ||
                RedisMessageConstants.DEVICE_ID.equals(refreshMsg.getDeviceId())) {
            return;
        }
        log.debug("refresh local, cache flag:{} name:{}, key:{}", refreshMsg.getFlag(),
                refreshMsg.getCacheName(), refreshMsg.getKey());
        if (refreshMsg
                .getFlag()
                .equals(CacheRefreshMsg.Flag.DATA)) {
            onGlobalDataChanged(refreshMsg.getCacheName(), refreshMsg.getKey());
        } else if (refreshMsg
                .getFlag()
                .equals(CacheRefreshMsg.Flag.CONFIG)) {
            onConfigChanged(refreshMsg.getCacheName());
        }
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            try {
                listenerContainer.addMessageListener(this,
                        new ChannelTopic(RedisMessageConstants.SYNC_TOPIC));
            } catch (Exception e) {
                log.error("onApplicationEvent", e);
            }
        }
    }
}
package com.chainxi.system.config.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author : ChainXi
 * @Date : 2024/5/3 18:47
 * @Desc :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheRefreshMsg implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    public enum Flag {
        DATA, CONFIG;
    }

    private Flag flag;
    private String cacheName;
    private Object key;
    private String deviceId;
}
package com.chainxi.system.config.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 20:57
 * @Desc :
 */
public class MultiLevelCache extends AbstractValueAdaptingCache {
    private final List<Cache> caches = new ArrayList<>();
    private final CacheConfigSynchronizer cacheConfigSynchronizer;
    private final List<Integer> defaultConfig;
    private final CacheDataChangedBroadcast cacheDataChangedBroadcast;
    private final String name;

    /**
     * Create an {@code AbstractValueAdaptingCache} with the given setting.
     *
     * @param allowNullValues whether to allow for {@code null} values
     */
    protected MultiLevelCache(String name,
            boolean allowNullValues,
            CacheConfigSynchronizer cacheConfigSynchronizer,
            List<Integer> defaultConfig,
            CacheDataChangedBroadcast cacheDataChangedBroadcast) {
        super(allowNullValues);
        this.name                      = name;
        this.cacheConfigSynchronizer   = cacheConfigSynchronizer;
        this.defaultConfig             = defaultConfig;
        this.cacheDataChangedBroadcast = cacheDataChangedBroadcast;
        syncConfig();
    }

    @Override
    protected Object lookup(@Nonnull Object key) {
        for (int i = caches.size() - 1; i >= 0; i--) {
            Cache cache = caches.get(i);
            Object result = Optional.ofNullable(cache.get(key)).map(ValueWrapper::get).orElse(null);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public Object getNativeCache() {
        return this;
    }

    /**
     * <a href="https://blog.csdn.net/weixin_42189048/article/details/122756791">CacheInterceptor相关</a>
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(@Nonnull Object key, @Nonnull Callable<T> valueLoader) {
        ValueWrapper result = get(key);
        if (result != null) {
            return (T) result.get();
        }
        synchronized (this) {
            result = get(key);
            if (result != null) {
                return (T) result.get();
            }
            T value;
            try {
                value = valueLoader.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            put(key, value);
            return value;
        }
    }

    @Override
    public void put(@Nonnull Object key, Object value) {
        caches.forEach(cache -> cache.put(key, value));
        cacheDataChangedBroadcast.syncData(getName(), key);
    }

    @Override
    public void evict(@Nonnull Object key) {
        caches.forEach(cache -> cache.evict(key));
        cacheDataChangedBroadcast.syncData(getName(), key);
    }

    @Override
    public void clear() {
        caches.forEach(Cache::clear);
        cacheDataChangedBroadcast.syncData(getName(), SimpleKey.EMPTY);
    }

    public void evictLocal(Object key) {
        for (int i = 1; i < caches.size(); i++) {
            if (key != null) {
                caches.get(i).evict(key);
            } else {
                caches.get(i).clear();
            }
        }
    }

    public void syncConfig() {
        cacheConfigSynchronizer.sync(getName(), caches, defaultConfig);
    }
}
package com.chainxi.system.config.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import jakarta.annotation.Nonnull;
import java.util.*;

/**
 * @Author : ChainXi
 * @Date : 2024/5/9 2:28
 * @Desc :
 */

public class MultiLevelCacheManager extends AbstractTransactionSupportingCacheManager {
    private final CacheConfigSynchronizer cacheConfigSynchronizer;
    private final CacheDataChangedBroadcast cacheDataChangedBroadcast;
    private final Map<String, List<Integer>> initialCacheConfiguration = new LinkedHashMap<>();
    protected boolean allowNullValues = true;

    public MultiLevelCacheManager(CacheConfigSynchronizer cacheConfigSynchronizer,
            CacheDataChangedBroadcast cacheDataChangedBroadcast,
            Map<String, List<Integer>> initialCacheConfiguration) {
        this.cacheConfigSynchronizer   = cacheConfigSynchronizer;
        this.cacheDataChangedBroadcast = cacheDataChangedBroadcast;
        this.initialCacheConfiguration.putAll(initialCacheConfiguration);
    }

    private MultiLevelCache createMultilevelCache(String name, List<Integer> defaultConfig) {
        return new MultiLevelCache(name,
                allowNullValues,
                cacheConfigSynchronizer,
                defaultConfig,
                cacheDataChangedBroadcast);
    }

    @Override
    public MultiLevelCache getCache(@Nonnull String name) {
        return (MultiLevelCache) super.getCache(name);
    }

    @Nonnull
    @Override
    protected Collection<? extends Cache> loadCaches() {
        List<Cache> caches = new LinkedList<>();
        for (Map.Entry<String, List<Integer>> entry : initialCacheConfiguration.entrySet()) {
            caches.add(createMultilevelCache(entry.getKey(), entry.getValue()));
        }
        return caches;
    }

    @Override
    protected Cache getMissingCache(@Nonnull String name) {
        return createMultilevelCache(name, Collections.singletonList(3600000));
    }
}
package com.chainxi.system.config.cache;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.cache.interceptor.SimpleKey;

import java.io.IOException;

public class SimpleKeyDeserializer extends JsonDeserializer<SimpleKey> {

    public static final SimpleKeyDeserializer INSTANCE = new SimpleKeyDeserializer();

    @Override
    public SimpleKey deserialize(JsonParser jsonParser,
            DeserializationContext ctx) throws IOException {
        JsonToken jsonToken = jsonParser.currentToken();
        Object[] elements = null;
        while (!jsonParser.isClosed()) {
            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                if ("params".equals(jsonParser.currentName())) {
                    jsonParser.nextToken();
                    elements = jsonParser.readValueAs(Object[].class);
                }
            } else if (JsonToken.END_OBJECT.equals(jsonToken)) {
                break;
            }
            jsonToken = jsonParser.nextToken();
        }
        return elements != null ? new SimpleKey(elements) : null;
    }


}


```