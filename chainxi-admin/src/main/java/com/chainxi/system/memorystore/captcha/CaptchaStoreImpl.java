package com.chainxi.system.memorystore.captcha;

import com.chainxi.system.constants.system.CacheKeyConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/9/17 21:48
 * @Desc :
 */
@Service
public class CaptchaStoreImpl implements CaptchaStore {
    @Nonnull
    @Override
    @Cacheable(value = CacheKeyConstants.CAPTCHA_CODE_KEY, key = "#key",unless = "#result == null")
    public String putCaptcha(@Nonnull String key, String code) {
        return code;
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.CAPTCHA_CODE_KEY, key = "#key")
    public void removeCaptcha(@Nonnull String key) {

    }
}
