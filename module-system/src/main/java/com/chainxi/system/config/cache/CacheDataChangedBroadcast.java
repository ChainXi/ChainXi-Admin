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
