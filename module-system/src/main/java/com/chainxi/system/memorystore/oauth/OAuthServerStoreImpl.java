package com.chainxi.system.memorystore.oauth;

import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.respvo.auth.OAuthClientCertificateRespVo;
import jakarta.annotation.Nonnull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author : ChainXi
 * @Date : 2025/1/30 0:06
 * @Desc :
 */
@Service
public class OAuthServerStoreImpl implements OAuthServerStore {
    @Override
    @Cacheable(value = CacheKeyConstants.OAUTH_TICKET, key = "#code",unless = "#result == null")
    public OAuthClientCertificateRespVo getCertificateByCode(Long code) {
        return null;
    }

    @Nonnull
    @CachePut(value = CacheKeyConstants.OAUTH_TICKET, key = "#code")
    @Override
    public OAuthClientCertificateRespVo putCode(Long code, OAuthClientCertificateRespVo userId) {
        return userId;
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.OAUTH_TICKET, key = "#code")
    public void removeCode(Long code) {

    }
}
