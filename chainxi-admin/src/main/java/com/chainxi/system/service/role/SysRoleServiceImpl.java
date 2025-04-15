package com.chainxi.system.service.role;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.chainxi.common.web.component.ContextLoader;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.convert.auth.SysRoleConvert;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.mapper.role.SysRoleMapper;
import com.chainxi.system.reqvo.role.RoleCreateReqVo;
import com.chainxi.system.reqvo.role.RoleExportReqVO;
import com.chainxi.system.reqvo.role.RolePageReqVO;
import com.chainxi.system.reqvo.role.RoleUpdateReqVO;
import com.chainxi.system.service.auth.AuthService;
import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper roleMapper;
    private final AuthService authService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRole(RoleCreateReqVo reqVo, Integer type) {
        // 校验角色
        validateRoleDuplicate(reqVo.getName(), null);
        // 插入到数据库
        SysRoleDo role = SysRoleConvert.INSTANCE.convert(reqVo);
        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
        roleMapper.insert(role);
        // 返回
        return role.getId();
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.ROLE, key = "#reqVo.id")
    public void updateRole(RoleUpdateReqVO reqVo) {
        // 校验是否可以更新
        validateRoleForUpdate(reqVo.getId());
        // 校验角色的唯一字段是否重复
        validateRoleDuplicate(reqVo.getName(), reqVo.getId());

        // 更新到数据库
        SysRoleDo updateObj = SysRoleConvert.INSTANCE.convert(reqVo);
        roleMapper.updateById(updateObj);
    }

    @Override
    @CacheEvict(value = CacheKeyConstants.ROLE, key = "#id")
    public void updateRoleStatus(Long id, Integer status) {
        // 校验是否可以更新
        validateRoleForUpdate(id);

        // 更新状态
        SysRoleDo sysRoleDo = new SysRoleDo();
        sysRoleDo.setId(id);
        sysRoleDo.setStatus(status);
        roleMapper.updateById(sysRoleDo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheKeyConstants.ROLE, key = "#id")
    public void deleteRole(Long id) {
        // 校验是否可以更新
        validateRoleForUpdate(id);
        // 标记删除
        roleMapper.deleteById(id);
        // 删除相关数据
        authService.processRoleDeleted(id);
    }

    /**
     * 校验角色的唯一字段是否重复
     * <p>
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param id   角色编号
     */
    @VisibleForTesting
    void validateRoleDuplicate(String name, Long id) {
        // 1. 该 name 名字被其它角色所使用
        SysRoleDo role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.ROLE_NAME_DUPLICATE, name);
        }
    }

    /**
     * 校验角色是否可以被更新
     *
     * @param id 角色编号
     */
    @VisibleForTesting
    void validateRoleForUpdate(Long id) {
        SysRoleDo roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            throw new BizException(ErrorCodeConstants.ROLE_NOT_EXISTS);
        }
    }

    @Override
    public SysRoleDo getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    @Cacheable(value = CacheKeyConstants.ROLE, key = "#id", unless = "#result == null")
    public SysRoleDo getRoleFromCache(Long id) {
        return roleMapper.selectById(id);
    }


    @Override
    public List<SysRoleDo> getRoleListByStatus(Collection<Integer> statuses) {
        return roleMapper.selectListByStatus(statuses);
    }

    @Override
    public List<SysRoleDo> getRoleList() {
        return roleMapper.selectList();
    }

    @Override
    public List<SysRoleDo> getRoleList(Collection<Long> roleId) {
        if (CollectionUtil.isEmpty(roleId)) {
            return Collections.emptyList();
        }
        return roleMapper.selectBatchIds(roleId);
    }

    @Override
    public List<SysRoleDo> getRoleListFromCache(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // 这里采用 for 循环从缓存中获取，主要考虑 Spring CacheManager 无法批量操作的问题
        SysRoleServiceImpl bean = ContextLoader.getBean(getClass());
        return ids.stream().map(bean::getRoleFromCache).collect(Collectors.toList());
    }

    @Override
    public PageResult<SysRoleDo> getRolePage(RolePageReqVO reqVo) {
        return roleMapper.selectPage(reqVo);
    }

    @Override
    public List<SysRoleDo> getRoleList(RoleExportReqVO reqVo) {
        return roleMapper.selectList(reqVo);
    }

    @Override
    public void validateRoleList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        // 获得角色信息
        List<SysRoleDo> roles = roleMapper.selectBatchIds(ids);
        Map<Long, SysRoleDo> roleMap = roles.stream().collect(Collectors.toMap(SysRoleDo::getId,
                Function.identity()));
        // 校验
        ids.forEach(id -> {
            SysRoleDo role = roleMap.get(id);
            if (role == null) {
                throw new BizException(ErrorCodeConstants.ROLE_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(role.getStatus())) {
                throw new BizException(ErrorCodeConstants.ROLE_IS_DISABLE,
                        role.getName());
            }
        });
    }

}
