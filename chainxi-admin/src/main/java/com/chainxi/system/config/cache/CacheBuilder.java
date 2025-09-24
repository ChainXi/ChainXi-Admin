package com.chainxi.system.config.cache;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 22:22
 * @Desc :
 */
public interface CacheBuilder {
    MetaCache build(@Nonnull String name, @Nonnull Integer expireTime);
}
