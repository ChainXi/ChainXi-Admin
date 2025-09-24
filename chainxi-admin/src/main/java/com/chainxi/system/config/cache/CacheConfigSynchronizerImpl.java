package com.chainxi.system.config.cache;

import com.chainxi.system.bo.SysCacheInfoBo;
import com.chainxi.system.convert.cache.SysCacheInfoConvert;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.mapper.cache.SysCacheInfoMapper;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2024/5/5 22:03
 * @Desc :
 */
@Slf4j
@RequiredArgsConstructor
public class CacheConfigSynchronizerImpl implements CacheConfigSynchronizer {
    private final SysCacheInfoMapper sysCacheInfoMapper;
    private final List<CacheBuilder> builders;

    public SysCacheInfoBo getInfo(String name) {
        SysCacheInfoDo sysCacheInfoDo = sysCacheInfoMapper.selectOneByName(name);
        return sysCacheInfoDo != null ?
                SysCacheInfoConvert.INSTANCE.convert2CacheInfoBo(sysCacheInfoDo) :
                null;
    }

    @Override
    public void sync(@Nonnull String name, @Nonnull List<MetaCache> currentCaches,
            List<CacheExpireTime> defaultConfig, Boolean localOnly) {
        SysCacheInfoBo info = getInfo(name);
        List<CacheExpireTime> expireTimes;
        if (info != null && info.getExpireTimes() != null) {
            expireTimes = info.getExpireTimes();
        } else {
            expireTimes = defaultConfig;
        }
        ArrayList<MetaCache> caches = new ArrayList<>(currentCaches);
        currentCaches.clear();
        caches.forEach(cache -> {
            if (!localOnly ||
                    cache.getSource().storageType.equals(StorageCache.StorageType.LOCAL)) {
                cache
                        .getRef()
                        .clear();
            }
        });
        for (int i = 0; i < expireTimes.size(); i++) {
            Integer expireTime = expireTimes
                    .get(i)
                    .getExpireTime();
            if (expireTime != -1) {
                currentCaches.add(builders
                        .get(i)
                        .build(name, expireTime));
            }
        }
        log.debug("cacheName:{},currentCaches:{},expireTimes:{}", name, currentCaches, expireTimes);
    }
}
