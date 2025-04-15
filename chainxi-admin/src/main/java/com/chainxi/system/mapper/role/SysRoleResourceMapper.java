package com.chainxi.system.mapper.role;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.system.entity.role.SysRoleResourceDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleResourceMapper extends BaseMapperX<SysRoleResourceDo> {

    default void deleteByRoleId(Long roleId) {
        delete(getBaseQueryWrapper().eq(SysRoleResourceDo::getRoleId, roleId));
    }
}
