package com.chainxi.system.service.auth;

import com.chainxi.system.config.captcha.Captcha;

import jakarta.annotation.Nonnull;
import java.io.IOException;

/**
 * 验证码处理
 *
 * @author chainxi
 */
public interface CaptchaService
{
    Boolean getCaptchaEnabled();
    /**
     * 生成验证码
     */
    Captcha createCaptcha() throws IOException;

    /**
     * 校验验证码
     *
     * @return
     */
    Boolean consumeCaptcha(@Nonnull String userInputCode,@Nonnull String uuid);
}
