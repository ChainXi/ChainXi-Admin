package com.chainxi.system.reqvo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginReqVo {
    private String trace;
    private String userName;
    private String password;
    private String uuid;
    private String captcha;
}
