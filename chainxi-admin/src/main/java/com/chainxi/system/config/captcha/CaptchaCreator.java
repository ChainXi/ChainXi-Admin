package com.chainxi.system.config.captcha;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/9/18 0:10
 * @Desc :
 */
public interface CaptchaCreator {
    @Nonnull
    Captcha createCaptcha();

    @Nonnull
    Boolean verify(@Nonnull String code,@Nonnull String userInputCode);
}
