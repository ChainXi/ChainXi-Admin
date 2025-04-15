package com.chainxi.system.entity.token;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;


@TableName(value = "sys_ra_token", autoResultMap = true)
// 由于 Oracle 的 SEQ 的名字长度有限制，所以就先用 system_oauth2_access_token_seq 吧，反正也没啥问题
@KeySequence("system_oauth2_access_token_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class RefreshTokenDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 编号，数据库字典
     */
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户编号
     */
    private Long clientId;
    /**
     * 用户编号
     */
    private Long did;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 过期时间
     */
    private LocalDateTime atExpiresTime;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}
