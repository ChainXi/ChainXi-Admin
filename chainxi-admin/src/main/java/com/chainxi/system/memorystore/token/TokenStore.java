package com.chainxi.system.memorystore.token;

import com.chainxi.system.bo.AuthUserBo;

import jakarta.annotation.Nullable;


/**
 * 不进行持久化,直接存储在内存数据库
 */
public interface TokenStore {

    @Nullable
    AuthUserBo getAuthUser(String accessToken);

    void removeAccessToken(String accessToken);

    AuthUserBo setAccessToken(String accessToken, AuthUserBo authUserBo);

}
