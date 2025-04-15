package com.chainxi.system.memorystore.oauth;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/9/17 21:46
 * @Desc :
 */
public interface OAuthClientStore {
    Long getUser(Long accessToken);
    Long putUser(Long accessToken,Long userId);
    void removeUser(@Nonnull Long accessToken);
}
