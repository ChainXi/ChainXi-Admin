package com.chainxi.system.config.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import jakarta.annotation.Nonnull;
import java.time.Duration;

/**
 * @Author : ChainXi
 * @Date : 2024/5/8 3:29
 * @Desc :
 */
@RequiredArgsConstructor
public class CacheBuilderRedisImpl implements CacheBuilder<RedisCache> {
    private final RedisCacheWriter cacheWriter;
    private final RedisCacheConfiguration defaultCacheConfig;


    @Override
    public RedisCache build(@Nonnull String name, @Nonnull Integer expireTime) {
        return new RedisCachePublic(name,
                cacheWriter,
                expireTime > 0 ?
                        defaultCacheConfig.entryTtl(Duration.ofMillis(expireTime)) :
                        defaultCacheConfig);
    }

    /**
     * 原生构造器为protected修饰,空继承解决
     */
    static class RedisCachePublic extends RedisCache {
        /**
         * Create new {@link RedisCache}.
         *
         * @param name        must not be {@literal null}.
         * @param cacheWriter must not be {@literal null}.
         * @param cacheConfig must not be {@literal null}.
         */
        public RedisCachePublic(String name,
                RedisCacheWriter cacheWriter,
                RedisCacheConfiguration cacheConfig) {
            super(name, cacheWriter, cacheConfig);
        }
    }
}
