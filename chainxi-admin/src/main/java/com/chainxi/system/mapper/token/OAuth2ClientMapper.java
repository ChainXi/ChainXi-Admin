package com.chainxi.system.mapper.token;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.token.OAuth2ClientDo;
import com.chainxi.system.reqvo.auth.OAuth2ClientInfoPageReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OAuth2ClientMapper extends BaseMapperX<OAuth2ClientDo> {


    default PageResult<OAuth2ClientDo> selectPage(OAuth2ClientInfoPageReqVo reqVo) {
        return selectPage(reqVo, getBaseQueryWrapper().likeIfPresent(OAuth2ClientDo::getName,
                reqVo.getName()));
    }

    default OAuth2ClientDo selectByClientId(Long clientId) {
        return selectOne(getBaseQueryWrapper().eq(OAuth2ClientDo::getClientId, clientId));
    }

    default List<OAuth2ClientDo> selectLogoutCallUrls(List<Long> clientIds) {
        return selectList(getBaseQueryWrapper()
                .isNotNull(OAuth2ClientDo::getLogoutCall)
                .in(OAuth2ClientDo::getClientId, clientIds)
                .select(OAuth2ClientDo::getClientId, OAuth2ClientDo::getLogoutCall));
    }
}
