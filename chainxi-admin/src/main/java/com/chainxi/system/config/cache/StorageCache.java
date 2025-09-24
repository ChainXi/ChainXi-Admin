package com.chainxi.system.config.cache;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StorageCache {
    CAFFEINE(StorageType.LOCAL),
    REDIS(StorageType.REMOTE);
    public final StorageType storageType;
    public enum StorageType {
        LOCAL, REMOTE
    }
}