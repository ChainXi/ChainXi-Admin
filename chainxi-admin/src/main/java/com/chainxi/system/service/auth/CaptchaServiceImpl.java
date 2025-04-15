package com.chainxi.system.service.auth;

import com.chainxi.common.web.exception.BizException;
import com.chainxi.system.config.captcha.Captcha;
import com.chainxi.system.config.captcha.CaptchaCreator;
import com.chainxi.system.config.captcha.CaptchaProperties;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.memorystore.captcha.CaptchaStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.annotation.Nonnull;

/**
 * 验证码实现处理
 *
 * @author chainxi
 */
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {
    private final CaptchaCreator captchaCreator;
    private final CaptchaProperties properties;
    private final CaptchaStore captchaStore;

    @Override
    public Boolean getCaptchaEnabled() {
        return properties.getEnabled();
    }

    /**
     * 生成验证码
     */
    @Override
    public Captcha createCaptcha() {
        // 保存验证码信息
        Captcha captcha = captchaCreator.createCaptcha();
        captchaStore.putCaptcha(captcha.getUuid(), captcha.getReqText());
        return captcha;
    }

    /**
     * 校验验证码
     */
    @Override
    public Boolean consumeCaptcha(@Nonnull String userInputCode, @Nonnull String uuid) {
        if (!StringUtils.hasText(userInputCode)) {
            throw new BizException(ErrorCodeConstants.CAPTCHA_INVALID);
        }
        if (!StringUtils.hasText(uuid)) {
            throw new BizException(ErrorCodeConstants.CAPTCHA_INVALID);
        }
        String code = captchaStore.putCaptcha(uuid, null);
        captchaStore.removeCaptcha(uuid);
        if (code != null && captchaCreator.verify(code, userInputCode)) {
            return Boolean.TRUE;
        } else {
            throw new BizException(ErrorCodeConstants.CAPTCHA_INVALID);
        }
    }
}
