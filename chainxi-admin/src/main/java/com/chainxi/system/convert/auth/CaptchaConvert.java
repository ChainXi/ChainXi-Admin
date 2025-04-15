package com.chainxi.system.convert.auth;

import com.chainxi.system.config.captcha.Captcha;
import com.chainxi.system.respvo.auth.CaptchaRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.io.IOException;

@Mapper
public interface CaptchaConvert {
    CaptchaConvert INSTANCE = Mappers.getMapper(CaptchaConvert.class);

    default CaptchaRespVo convert(Captcha captcha) {
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(captcha.getImage(), "png", os);
        } catch (IOException e) {
            return null;
        }
        return new CaptchaRespVo().setUuid(captcha.getUuid()).setImage(os.toByteArray());
    }

}
