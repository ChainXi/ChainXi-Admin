package com.chainxi.system.service.role;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.reqvo.role.RoleCreateReqVo;
import com.chainxi.system.reqvo.role.RoleExportReqVO;
import com.chainxi.system.reqvo.role.RolePageReqVO;
import com.chainxi.system.reqvo.role.RoleUpdateReqVO;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;

public interface SysRoleService {

    /**
     * 创建角色
     *
     * @param reqVo 创建角色信息
     * @param type  角色类型
     * @return 角色编号
     */
    Long createRole(@Valid RoleCreateReqVo reqVo, Integer type);

    /**
     * 更新角色
     *
     * @param reqVo 更新角色信息
     */
    void updateRole(@Valid RoleUpdateReqVO reqVo);

    /**
     * 删除角色
     *
     * @param id 角色编号
     */
    void deleteRole(Long id);

    /**
     * 更新角色状态
     *
     * @param id     角色编号
     * @param status 状态
     */
    void updateRoleStatus(Long id, Integer status);

    /**
     * 获得角色
     *
     * @param id 角色编号
     * @return 角色
     */
    SysRoleDo getRole(Long id);

    /**
     * 获得角色，从缓存中
     *
     * @param id 角色编号
     * @return 角色
     */
    SysRoleDo getRoleFromCache(Long id);

    /**
     * 获得角色列表
     *
     * @param ids 角色编号数组
     * @return 角色列表
     */
    List<SysRoleDo> getRoleList(Collection<Long> ids);

    /**
     * 获得角色数组，从缓存中
     *
     * @param ids 角色编号数组
     * @return 角色数组
     */
    List<SysRoleDo> getRoleListFromCache(Collection<Long> ids);

    /**
     * 获得角色列表
     *
     * @param statuses 筛选的状态
     * @return 角色列表
     */
    List<SysRoleDo> getRoleListByStatus(Collection<Integer> statuses);

    /**
     * 获得所有角色列表
     *
     * @return 角色列表
     */
    List<SysRoleDo> getRoleList();

    /**
     * 获得角色分页
     *
     * @param reqVo 角色分页查询
     * @return 角色分页结果
     */
    PageResult<SysRoleDo> getRolePage(RolePageReqVO reqVo);

    /**
     * 获得角色列表
     *
     * @param reqVo 列表查询
     * @return 角色列表
     */
    List<SysRoleDo> getRoleList(RoleExportReqVO reqVo);

    /**
     * 校验角色们是否有效。如下情况，视为无效：
     * 1. 角色编号不存在
     * 2. 角色被禁用
     *
     * @param ids 角色编号数组
     */
    void validateRoleList(Collection<Long> ids);


}
