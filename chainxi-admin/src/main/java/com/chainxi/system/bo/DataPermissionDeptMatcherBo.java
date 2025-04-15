package com.chainxi.system.bo;

import com.chainxi.system.enums.auth.DataPermissionDeptMatchBucketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 缓存信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPermissionDeptMatcherBo {
    private static final long serialVersionUID = -1L;
    /**
     * 匹配类型
     */
    private DataPermissionDeptMatchBucketType matchType;
    /**
     * 检测目标
     */
    private Set<Long> deptIds;
}

