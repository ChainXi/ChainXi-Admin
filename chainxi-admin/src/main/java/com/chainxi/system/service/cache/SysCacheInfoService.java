package com.chainxi.system.service.cache;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.reqvo.cache.CacheInfoPageReqVo;
import com.chainxi.system.reqvo.cache.CacheInfoUpdateReqVo;

import jakarta.annotation.Nullable;

public interface SysCacheInfoService {
    void clearCache(String name);

    void deleteCacheInfo(String name);

    void createCacheInfo(CacheInfoUpdateReqVo reqVo);

    void updateCacheInfo(CacheInfoUpdateReqVo reqVo);

    PageResult<SysCacheInfoDo> selectPage(CacheInfoPageReqVo reqVo);

    @Nullable
    SysCacheInfoDo selectOne(String name);
}
