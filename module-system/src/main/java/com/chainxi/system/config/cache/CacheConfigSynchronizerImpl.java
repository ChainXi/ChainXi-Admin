package com.chainxi.system.config.cache;

import com.chainxi.system.bo.SysCacheInfoBo;
import com.chainxi.system.convert.cache.SysCacheInfoConvert;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.mapper.cache.SysCacheInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;

import jakarta.annotation.Nonnull;
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
    public void sync(@Nonnull String name,
            @Nonnull List<Cache> currentCaches,
            List<Integer> defaultConfig) {
        SysCacheInfoBo info = getInfo(name);
        List<Integer> expireTimes;
        if (info != null) {
            expireTimes = info.getExpireTimes();
        } else {
            expireTimes = defaultConfig;
        }
        ArrayList<Cache> caches = new ArrayList<>(currentCaches);
        currentCaches.clear();
        caches.forEach(Cache::clear);
        //TBD 对业务上仅非持久化存储数据有影响,是否清除旧的远端缓存?建议另外提供主动清除api
        for (int i = 0; i < expireTimes.size(); i++) {
            Integer expireTime = expireTimes.get(i);
            if (expireTime != -1) {
                currentCaches.add(builders.get(i).build(name, expireTime));
            }
        }

        /*
        if (currentCaches.size() >= expireTimes.size() + 1) {
            List<Cache> caches =
                    currentCaches.subList(expireTimes.size() + 1, currentCaches.size() + 1);
            caches.forEach(Cache::clear);
            caches.clear();
        }
        for (int i = 0; i < expireTimes.size(); i++) {
            Integer expireTime = expireTimes.get(i);
            if (i == currentCaches.size()) {
                currentCaches.add(builders.get(i).build(name, expireTime));
            } else if (i < currentCaches.size()) {
                //TBD 对业务上仅非持久化存储数据有影响,是否清除旧的远端缓存?建议另外提供主动清除api
                currentCaches.set(i, builders.get(i).build(name, expireTime));
            }
        }
        */
        log.debug("cacheName:{},currentCaches:{},expireTimes:{}", name, currentCaches, expireTimes);
    }
}
