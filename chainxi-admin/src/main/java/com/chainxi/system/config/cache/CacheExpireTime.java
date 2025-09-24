package com.chainxi.system.config.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ChainXi
 * @Date : 2025/9/22 1:07
 * @Desc :
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CacheExpireTime {
    private StorageCache storageCache;
    private Integer expireTime;
}
