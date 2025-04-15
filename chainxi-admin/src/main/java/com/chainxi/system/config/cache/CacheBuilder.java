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
