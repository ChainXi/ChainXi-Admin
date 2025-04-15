package com.chainxi.system.memorystore.captcha;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @Author : ChainXi
 * @Date : 2024/9/17 21:46
 * @Desc :
 */
public interface CaptchaStore {
    @Nullable
    String putCaptcha(@Nonnull String key,String code);
    void removeCaptcha(@Nonnull String key);
}
