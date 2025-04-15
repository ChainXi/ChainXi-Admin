package com.chainxi.system.reqvo.cache;

import lombok.Data;

@Data
public class CacheInfoUpdateReqVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 键值
     */
    private String name;
    /**
     * 过期时间
     */
    private Integer remoteExpireTime;
    /**
     * 本地过期时间
     */
    private Integer localExpireTime;
    /**
     * 备注
     */
    private String remark;
}
