package com.chainxi.system.reqvo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthCertificateReqVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private Long did;
    private Long code;
    private Long clientId;
    private String clientSecret;

}
