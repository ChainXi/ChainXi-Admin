package com.chainxi.system.config.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @Author : ChainXi
 * @Date : 2024/9/18 0:12
 * @Desc :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {
    private String uuid;
    private String reqText;
    private BufferedImage image;
}
