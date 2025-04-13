package com.chainxi.system.mapper.role;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.system.entity.role.SysRoleMenuDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysRoleMenuMapper extends BaseMapperX<SysRoleMenuDo> {
    default List<SysRoleMenuDo> getMenuIdsByRoleId(@Param("roleIds") Collection roleIds) {
        return selectList(getBaseQueryWrapper().in(SysRoleMenuDo::getRoleId, roleIds));
    }


    default void deleteListByMenuId(Long menuId) {
        delete(new LambdaQueryWrapper<SysRoleMenuDo>().eq(SysRoleMenuDo::getMenuId, menuId));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<SysRoleMenuDo>().eq(SysRoleMenuDo::getRoleId, roleId));
    }

}
