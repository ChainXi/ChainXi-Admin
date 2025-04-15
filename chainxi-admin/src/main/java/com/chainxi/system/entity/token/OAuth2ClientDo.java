package com.chainxi.system.entity.token;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @Author : ChainXi
 * @Date : 2025/2/8 1:31
 * @Desc :
 */
@TableName(value = "sys_oauth2_client", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2ClientDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 编号，数据库字典
     */
    private Long id;
    private Long clientId;
    private String name;
    private String secret;
    private String origin;
    private String logoutCall;
    /**
     * 应用描述
     */
    private String description;
}
