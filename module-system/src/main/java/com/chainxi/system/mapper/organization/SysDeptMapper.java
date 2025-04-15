package com.chainxi.system.mapper.organization;

import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.system.entity.organization.SysDeptDo;
import com.chainxi.system.reqvo.organization.DeptQueryReqVo;

import jakarta.annotation.Nonnull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门管理 数据层
 */
@Mapper
public interface SysDeptMapper extends BaseMapperX<SysDeptDo> {
    default List<SysDeptDo> selectList(@Nonnull DeptQueryReqVo reqVo) {
        return selectList(getBaseQueryWrapper()
                .gtIfPresent(SysDeptDo::getCreateTime,
                        reqVo.getCreateTime() != null && reqVo.getCreateTime().length >= 1 ?
                                reqVo.getCreateTime()[0] :
                                null)
                .ltIfPresent(SysDeptDo::getCreateTime,
                        reqVo.getCreateTime() != null && reqVo.getCreateTime().length >= 2 ?
                                reqVo.getCreateTime()[1] :
                                null)
                .likeIfPresent(SysDeptDo::getDeptName, reqVo.getDeptName())
                .eqIfPresent(SysDeptDo::getStatus, reqVo.getStatus()));
    }

    default List<SysDeptDo> selectDeptMapList() {
        return selectList(getBaseQueryWrapper()
                .eqIfPresent(SysDeptDo::getStatus, CommonStatusEnum.ENABLE)
                .select(SysDeptDo::getId, SysDeptDo::getDeptName));
    }

    default List<Long> selectDeptIds() {
        return selectObjs(getBaseQueryWrapper()
                .eqIfPresent(SysDeptDo::getStatus, CommonStatusEnum.ENABLE)
                .select(SysDeptDo::getId));
    }

    default int deleteNode(Long pid) {
        return delete(getBaseQueryWrapper().ampersandEq(SysDeptDo::getId, pid, pid));
    }

    default int disableNode(Long pid) {
        return update(new SysDeptDo().setStatus(CommonStatusEnum.DISABLE),
                getBaseQueryWrapper().ampersandEq(SysDeptDo::getId, pid, pid));
    }
}
