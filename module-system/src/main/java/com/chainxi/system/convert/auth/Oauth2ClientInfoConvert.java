package com.chainxi.system.convert.auth;

import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.entity.token.OAuth2ClientDo;
import com.chainxi.system.reqvo.auth.Oauth2ClientInfoUpdateReqVo;
import com.chainxi.system.reqvo.cache.CacheInfoUpdateReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Oauth2ClientInfoConvert {

    Oauth2ClientInfoConvert INSTANCE = Mappers.getMapper(Oauth2ClientInfoConvert.class);

    OAuth2ClientDo convert2Do(Oauth2ClientInfoUpdateReqVo reqVo);

    SysCacheInfoDo convert2Do(CacheInfoUpdateReqVo reqVo);

}
