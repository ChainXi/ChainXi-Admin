package com.chainxi.system.config.captcha;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.UUID;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/9/18 0:22
 * @Desc :
 */
public class TextCaptchaCreator implements CaptchaCreator {
    @Nonnull
    @Override
    public Captcha createCaptcha() {
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        AbstractCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50);
        lineCaptcha.setGenerator(randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();

        return new Captcha(UUID.randomUUID().toString(true), lineCaptcha.getCode(), lineCaptcha.getImage());
    }

    @Nonnull
    @Override
    public Boolean verify(@Nonnull String code, @Nonnull String userInputCode) {
        return code.equalsIgnoreCase(userInputCode);
    }
}
