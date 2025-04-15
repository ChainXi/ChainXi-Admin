package com.chainxi.system.reqvo.auth;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author : ChainXi
 * @Date : 2025/2/8 1:31
 * @Desc :
 */
@Data
public class Oauth2ClientInfoUpdateReqVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 编号，数据库字典
     */
    private Long id;
    private Long clientId;
    private String name;
    private String origin;
    private String logoutCall;
    /**
     * 应用描述
     */
    private String description;
}
