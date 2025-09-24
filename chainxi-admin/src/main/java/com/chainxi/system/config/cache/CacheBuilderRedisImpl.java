package com.chainxi.system.config.cache;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.time.Duration;

/**
 * @Author : ChainXi
 * @Date : 2024/5/8 3:29
 * @Desc :
 */
@RequiredArgsConstructor
public class CacheBuilderRedisImpl implements CacheBuilder {
    private final RedisCacheManager redisCacheManager;
    private final RedisCacheConfiguration defaultCacheConfig;

    @Override
    public MetaCache build(@Nonnull String name, @Nonnull Integer expireTime) {
        return new MetaCache(redisCacheManager.createRedisCache(name,expireTime > 0 ?
                defaultCacheConfig.entryTtl(Duration.ofMillis(expireTime)) :
                defaultCacheConfig),StorageCache.REDIS);
    }
}
