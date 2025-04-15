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
