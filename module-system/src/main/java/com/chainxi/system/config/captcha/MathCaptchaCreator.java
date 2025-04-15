package com.chainxi.system.config.captcha;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.lang.UUID;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/9/18 0:27
 * @Desc :
 */
public class MathCaptchaCreator implements CaptchaCreator {

    private final MathGenerator generator = new MathGenerator();

    @Nonnull
    @Override
    public Captcha createCaptcha() {
        AbstractCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        captcha.setGenerator(generator);
        captcha.createCode();
        return new Captcha(UUID.randomUUID().toString(true), captcha.getCode(), captcha.getImage());
    }

    @Nonnull
    @Override
    public Boolean verify(@Nonnull String code, @Nonnull String userInputCode) {
        return generator.verify(code,userInputCode);
    }
}
