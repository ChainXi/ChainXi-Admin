package com.chainxi.system.config.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cache.Cache;

/**
 * @Author : ChainXi
 * @Date : 2025/9/21 22:16
 * @Desc :
 */
@AllArgsConstructor
@Data
public class MetaCache {
    private Cache ref;
    private StorageCache source;
}
