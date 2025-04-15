package com.chainxi.system.respvo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ChainXi
 * @Date : 2024/9/18 3:01
 * @Desc :
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CaptchaRespVo {
    private String uuid;
    private byte[] image;
}
