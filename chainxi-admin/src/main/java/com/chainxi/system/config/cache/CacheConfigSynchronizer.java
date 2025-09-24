package com.chainxi.system.config.cache;

import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 21:59
 * @Desc :
 */
public interface CacheConfigSynchronizer {
    void sync(@Nonnull String name, @Nonnull List<MetaCache> list, List<CacheExpireTime> defaultConfig,Boolean localOnly);
}
