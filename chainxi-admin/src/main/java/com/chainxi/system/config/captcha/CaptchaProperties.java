package com.chainxi.system.config.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 验证码配置
 * 
 * @author chainxi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "chainxi.security.captcha")
public class CaptchaProperties
{
    /**
     * 验证码开关
     */
    private Boolean enabled = Boolean.FALSE;

    /**
     * 验证码类型（math 数组计算 char 字符）
     */
    private CaptchaType type = CaptchaType.MATH;

    public enum CaptchaType{
        MATH,CHAR
    }
}
