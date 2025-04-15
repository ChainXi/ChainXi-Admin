package com.chainxi.system.service.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.token.OAuth2ClientDo;
import com.chainxi.system.reqvo.auth.OAuth2ClientInfoPageReqVo;
import com.chainxi.system.reqvo.auth.Oauth2ClientInfoUpdateReqVo;
import jakarta.annotation.Nullable;

public interface OAuth2Service {

    void deleteClient(Long clientId);

    void createClient(Oauth2ClientInfoUpdateReqVo reqVo);

    void updateClient(Oauth2ClientInfoUpdateReqVo reqVo);

    PageResult<OAuth2ClientDo> selectPage(OAuth2ClientInfoPageReqVo reqVo);

    @Nullable
    OAuth2ClientDo selectOne(Long clientId);
}
