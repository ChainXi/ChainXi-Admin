package com.chainxi.system.service.organization;

import com.chainxi.system.entity.organization.SysDeptDo;
import com.chainxi.system.reqvo.organization.DeptCreateReqVo;
import com.chainxi.system.reqvo.organization.DeptQueryReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateStatusReqVo;
import com.chainxi.system.respvo.organization.SysDeptDetailRespVo;
import com.chainxi.system.respvo.organization.SysDeptRespVo;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;

public interface SysDeptService {
    @Nullable
    SysDeptDetailRespVo selectOneByDeptId(@Nonnull Long id);
    @Nonnull
    List<SysDeptRespVo> selectList(@Nonnull DeptQueryReqVo reqVo);
    @Nonnull
    List<SysDeptDo> selectMapList();
    @Nonnull
    Boolean createDept(@Nonnull DeptCreateReqVo reqVo);
    @Nonnull
    Boolean updateDept(@Nonnull DeptUpdateReqVo reqVo);
    @Nonnull
    Integer deleteDept(@Nonnull Long id);

    Integer updateDeptStatus(@Nonnull DeptUpdateStatusReqVo reqVo);
}
