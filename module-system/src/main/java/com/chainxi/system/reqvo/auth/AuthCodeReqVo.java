package com.chainxi.system.reqvo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCodeReqVo {
    private String client;
    private String redirect;
}
