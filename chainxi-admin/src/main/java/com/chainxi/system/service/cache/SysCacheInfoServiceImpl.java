package com.chainxi.system.service.cache;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.config.cache.MultiLevelCache;
import com.chainxi.system.config.cache.MultiLevelCacheManager;
import com.chainxi.system.convert.cache.SysCacheInfoConvert;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.mapper.cache.SysCacheInfoMapper;
import com.chainxi.system.reqvo.cache.CacheInfoPageReqVo;
import com.chainxi.system.reqvo.cache.CacheInfoUpdateReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nullable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SysCacheInfoServiceImpl implements SysCacheInfoService {
    private final SysCacheInfoMapper sysCacheInfoMapper;
    private final MultiLevelCacheManager cacheManager;

    @Override
    public void clearCache(String name) {
        Optional.ofNullable(cacheManager.getCache(name)).ifPresent(MultiLevelCache::clear);
    }

    @Override
    public void deleteCacheInfo(String name) {
        Optional.ofNullable(cacheManager.getCache(name)).ifPresent(MultiLevelCache::clear);
        sysCacheInfoMapper.delete(name);
    }

    @Override
    public void createCacheInfo(CacheInfoUpdateReqVo reqVo) {
        sysCacheInfoMapper.insert(SysCacheInfoConvert.INSTANCE.convert2Do(reqVo));
    }

    @Override
    public void updateCacheInfo(CacheInfoUpdateReqVo reqVo) {
        sysCacheInfoMapper.updateById(SysCacheInfoConvert.INSTANCE.convert2Do(reqVo));
        Optional
                .ofNullable(cacheManager.getCache(reqVo.getName()))
                .ifPresent(MultiLevelCache::syncConfig);

    }

    @Override
    public PageResult<SysCacheInfoDo> selectPage(CacheInfoPageReqVo reqVo) {
        return sysCacheInfoMapper.selectPage(reqVo);
    }

    @Nullable
    @Override
    public SysCacheInfoDo selectOne(String name) {
        return sysCacheInfoMapper.selectOneByName(name);
    }

}

