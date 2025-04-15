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
