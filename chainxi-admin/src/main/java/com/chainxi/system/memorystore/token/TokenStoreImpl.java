package com.chainxi.system.memorystore.token;

import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.constants.system.CacheKeyConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Nullable;


/**
 * 不进行持久化,直接存储在内存数据库
 */
@Repository
public class TokenStoreImpl implements TokenStore {

    @Nullable
    @Cacheable(value = CacheKeyConstants.AUTH_USER, key = "#accessToken", unless = "true")
    public AuthUserBo getAuthUser(String accessToken) {
        return null;
    }

    @CacheEvict(value = CacheKeyConstants.AUTH_USER, key = "#accessToken")
    public void removeAccessToken(String accessToken) {
    }

    @CachePut(value = CacheKeyConstants.AUTH_USER, key = "#accessToken")
    public AuthUserBo setAccessToken(String accessToken, AuthUserBo authUserBo) {
        return authUserBo;
    }

}
