package com.chainxi.system.convert.cache;

import com.chainxi.system.bo.SysCacheInfoBo;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.reqvo.cache.CacheInfoUpdateReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

@Mapper
public interface SysCacheInfoConvert {

    SysCacheInfoConvert INSTANCE = Mappers.getMapper(SysCacheInfoConvert.class);

    default SysCacheInfoBo convert2CacheInfoBo(SysCacheInfoDo sysCacheInfoDo) {
        return new SysCacheInfoBo()
                .setName(sysCacheInfoDo.getName())
                .setExpireTimes(
                        Arrays.asList(
                                sysCacheInfoDo.getRemoteExpireTime(),
                                sysCacheInfoDo.getLocalExpireTime()
                        ));
    }

    SysCacheInfoDo convert2Do(CacheInfoUpdateReqVo reqVo);

}
