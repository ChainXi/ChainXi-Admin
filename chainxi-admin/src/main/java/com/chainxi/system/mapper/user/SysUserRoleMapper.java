package com.chainxi.system.mapper.user;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.entity.user.SysUserRoleDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author 25056
 * @description 针对表【sys_user_role】的数据库操作Mapper
 * @createDate 2023-09-10 00:04:47
 * @Entity com.chainxi.system.entity.UserRole
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapperX<SysUserRoleDo> {
    default List<SysUserRoleDo> selectListByUserId(Long userId) {
        return selectList(getBaseQueryWrapper().eq(SysUserRoleDo::getUserId, userId));
    }

    default void deleteListByUserIdAndRoleIdIds(Long userId, Collection<Long> roleIds) {
        delete(getBaseQueryWrapper()
                .eq(SysUserRoleDo::getUserId, userId)
                .in(SysUserRoleDo::getRoleId, roleIds));
    }

    default void deleteListByUserId(Long userId) {
        delete(getBaseQueryWrapper().eq(SysUserRoleDo::getUserId, userId));
    }

    default void deleteListByRoleId(Long roleId) {
        delete(getBaseQueryWrapper().eq(SysUserRoleDo::getRoleId, roleId));
    }

    List<Long> getRoleIdsByUserId(Long userId);

    List<SysRoleDo> getRolesByUserId(Long userId);
}




