package com.chainxi.system.memorystore.oauth;

import com.chainxi.system.respvo.auth.OAuthClientCertificateRespVo;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @Author : ChainXi
 * @Date : 2024/9/17 21:46
 * @Desc :
 */
public interface OAuthServerStore {
    @Nullable
    OAuthClientCertificateRespVo getCertificateByCode(@Nonnull Long code);
    @Nullable
    OAuthClientCertificateRespVo putCode(@Nonnull Long code, OAuthClientCertificateRespVo userId);
    void removeCode(@Nonnull Long code);
}
