package com.chainxi.system.config.captcha;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author chainxi
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaConfig {
    @Bean
    public CaptchaCreator captchaCreator(CaptchaProperties properties) {
        if (CaptchaProperties.CaptchaType.MATH.equals(properties.getType())) {
            return new MathCaptchaCreator();
        } else {
            return new TextCaptchaCreator();
        }
    }

}