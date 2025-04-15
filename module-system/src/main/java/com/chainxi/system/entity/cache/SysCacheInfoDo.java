package com.chainxi.system.entity.cache;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 缓存信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_cache_info")
public class SysCacheInfoDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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

