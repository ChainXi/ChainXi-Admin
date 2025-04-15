package com.chainxi.system.mapper.cache;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.reqvo.cache.CacheInfoPageReqVo;
import org.apache.ibatis.annotations.Mapper;

import jakarta.annotation.Nullable;

@Mapper
public interface SysCacheInfoMapper extends BaseMapperX<SysCacheInfoDo> {
    @Nullable
    default SysCacheInfoDo selectOneByName(String name) {
        return selectOne(getBaseQueryWrapper().eq(SysCacheInfoDo::getName, name));
    }

    default PageResult<SysCacheInfoDo> selectPage(CacheInfoPageReqVo reqVo) {
        return selectPage(reqVo,
                getBaseQueryWrapper().likeIfPresent(SysCacheInfoDo::getName, reqVo.getName()));
    }

    default void delete(String name) {
        delete(getBaseQueryWrapper().eq(SysCacheInfoDo::getName, name));
    }

}
