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
