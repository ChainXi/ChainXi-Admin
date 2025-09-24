package com.chainxi.system.reqvo.cache;

import com.chainxi.system.config.cache.CacheExpireTime;
import lombok.Data;

import java.util.List;

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
    private List<CacheExpireTime> expireTimes;
    /**
     * 备注
     */
    private String remark;
}
