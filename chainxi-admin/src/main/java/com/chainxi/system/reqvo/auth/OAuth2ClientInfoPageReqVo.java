package com.chainxi.system.reqvo.auth;

import com.chainxi.common.web.pojo.PageParam;
import lombok.Data;

import java.io.Serial;

@Data
public class OAuth2ClientInfoPageReqVo extends PageParam {
    @Serial
    private static final long serialVersionUID = -1L;
    private String name;
}
