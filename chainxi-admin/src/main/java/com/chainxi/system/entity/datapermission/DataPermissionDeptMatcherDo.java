package com.chainxi.system.entity.datapermission;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import com.chainxi.system.enums.auth.DataPermissionDeptMatchBucketType;
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
@TableName(value = "sys_data_permission_user_dept")
public class DataPermissionDeptMatcherDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 键值
     */
    private Long userId;
    /**
     * 匹配类型
     */
    private DataPermissionDeptMatchBucketType matchType;
    /**
     * 检测目标
     */
    private Long target;
}

