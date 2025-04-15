package com.chainxi.system.mapper.role;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.BaseDo;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.reqvo.role.RoleExportReqVO;
import com.chainxi.system.reqvo.role.RolePageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapperX<SysRoleDo> {
    default PageResult<SysRoleDo> selectPage(RolePageReqVO reqVo) {
        return selectPage(reqVo,
                getBaseQueryWrapper().likeIfPresent(SysRoleDo::getName, reqVo.getName())
                        .eqIfPresent(SysRoleDo::getStatus, reqVo.getStatus())
                        .betweenIfPresent(BaseDo::getCreateTime, reqVo.getCreateTime())
                        .orderByDesc(SysRoleDo::getId));
    }

    default List<SysRoleDo> selectList(RoleExportReqVO reqVo) {
        return selectList(getBaseQueryWrapper().likeIfPresent(SysRoleDo::getName, reqVo.getName())
                .eqIfPresent(SysRoleDo::getStatus, reqVo.getStatus())
                .betweenIfPresent(BaseDo::getCreateTime, reqVo.getCreateTime()));
    }

    default SysRoleDo selectByName(String name) {
        return selectOne(getBaseQueryWrapper().eq(SysRoleDo::getName, name));
    }

    default List<SysRoleDo> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(getBaseQueryWrapper().in(SysRoleDo::getStatus, statuses));
    }
}
