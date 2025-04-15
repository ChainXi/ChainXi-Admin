package com.chainxi.system.memorystore.oauth;

import com.chainxi.system.constants.system.CacheKeyConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author : ChainXi
 * @Date : 2025/2/2 0:33
 * @Desc :
 */
@Service
public class OAuthClientStoreImpl implements OAuthClientStore {
    @Override
    @Cacheable(value = CacheKeyConstants.OAUTH_CLIENT_TOKEN, key = "#accessToken",unless = "#result == null")
    public Long getUser(Long accessToken) {
        return null;
    }

    @Override
    @CachePut(value = CacheKeyConstants.OAUTH_CLIENT_TOKEN, key = "#accessToken")
    public Long putUser(Long accessToken, Long userId) {
        return userId;
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.OAUTH_CLIENT_TOKEN, key = "#accessToken")
    public void removeUser(Long accessToken) {

    }
}
