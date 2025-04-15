package com.chainxi.system.service.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.convert.auth.Oauth2ClientInfoConvert;
import com.chainxi.system.entity.token.OAuth2ClientDo;
import com.chainxi.system.mapper.token.OAuth2ClientMapper;
import com.chainxi.system.reqvo.auth.OAuth2ClientInfoPageReqVo;
import com.chainxi.system.reqvo.auth.Oauth2ClientInfoUpdateReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author : ChainXi
 * @Date : 2025/2/9 1:23
 * @Desc :
 */
@RequiredArgsConstructor
@Service
public class OAuth2ServiceImpl implements OAuth2Service {
    private final OAuth2ClientMapper oauth2ClientMapper;

    @Override
    public void deleteClient(Long clientId) {
        oauth2ClientMapper.deleteById(clientId);
    }

    @Override
    public void createClient(Oauth2ClientInfoUpdateReqVo reqVo) {
        oauth2ClientMapper.insert(Oauth2ClientInfoConvert.INSTANCE.convert2Do(reqVo));
    }

    @Override
    public void updateClient(Oauth2ClientInfoUpdateReqVo reqVo) {
        oauth2ClientMapper.updateById(Oauth2ClientInfoConvert.INSTANCE.convert2Do(reqVo));

    }

    @Override
    public PageResult<OAuth2ClientDo> selectPage(OAuth2ClientInfoPageReqVo reqVo) {
        return oauth2ClientMapper.selectPage(reqVo);
    }

    @Override
    public OAuth2ClientDo selectOne(Long clientId) {
        return oauth2ClientMapper.selectByClientId(clientId);
    }
}
