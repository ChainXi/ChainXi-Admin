package com.chainxi.system.service.datapermission;


import com.chainxi.system.reqvo.datapermission.DataPermissionDeptMatcherUpdateReqVo;
import com.chainxi.system.respvo.datapermission.DataPermissionDeptMatcherRespVo;

import jakarta.annotation.Nonnull;

/**
 * 后台资源管理Service
 * Created by macro on 2020/2/2.
 */
public interface DataPermissionService {
    @Nonnull
    DataPermissionDeptMatcherRespVo selectDeptMatchersByUserId(@Nonnull Long userId);
    Boolean updateDeptMatcher(@Nonnull DataPermissionDeptMatcherUpdateReqVo reqVo);
    Integer deleteDeptMatcherByUserId(@Nonnull Long userId);
}
