package com.chainxi.system.respvo.datapermission;

import com.chainxi.system.enums.auth.DataPermissionDeptMatchBucketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 缓存信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPermissionDeptMatcherRespVo {
    private static final long serialVersionUID = -1L;
    /**
     * 匹配类型
     */
    private DataPermissionDeptMatchBucketType matchType;
    /**
     * 检测目标
     */
    private List<Long> target;
}

