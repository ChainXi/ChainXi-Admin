package com.chainxi.system.convert.cache;

import com.chainxi.system.bo.SysCacheInfoBo;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.reqvo.cache.CacheInfoUpdateReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysCacheInfoConvert {

    SysCacheInfoConvert INSTANCE = Mappers.getMapper(SysCacheInfoConvert.class);

    SysCacheInfoBo convert2CacheInfoBo(SysCacheInfoDo sysCacheInfoDo);

    SysCacheInfoDo convert2Do(CacheInfoUpdateReqVo reqVo);
}