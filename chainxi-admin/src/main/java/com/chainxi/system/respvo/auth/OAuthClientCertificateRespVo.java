package com.chainxi.system.respvo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthClientCertificateRespVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private Long did;
    private Long userId;
    private Long clientId;
}
