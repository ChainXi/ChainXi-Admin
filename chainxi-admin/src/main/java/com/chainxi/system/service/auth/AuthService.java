package com.chainxi.system.service.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.bo.AuthRoleBo;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.entity.token.RefreshTokenDo;
import com.chainxi.system.reqvo.auth.AuthLoginReqVo;
import com.chainxi.system.reqvo.auth.AssignMenu;
import com.chainxi.system.reqvo.auth.AssignRoleMenuReqVo;
import com.chainxi.system.reqvo.auth.OAuthCertificateReqVo;
import com.chainxi.system.reqvo.token.TokenPageReqVO;
import com.chainxi.system.respvo.auth.TokenRARespVo;

import jakarta.annotation.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyPair;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AuthService {
    @Nullable
    String auth(Long did, String redirect, Long clientId);

    @Nullable
    TokenRARespVo applyCode(OAuthCertificateReqVo reqVo);

    @Nullable
    TokenRARespVo login(AuthLoginReqVo authLoginReqVo, Long did);

    Boolean logout(String user);

    KeyPair queryKeyPair();

    /**
     * 创建访问令牌
     * 注意：该流程中，会包含创建刷新令牌的创建
     * <p>
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param userId 授权范围
     * @param did
     * @return 访问令牌的信息
     */
    RefreshTokenDo createRAToken(Long clientId,Long userId, Long did);

    @Transactional
    Integer removeAuthUser(Long userId);

    /**
     * 刷新访问令牌
     * <p>
     * 参考 DefaultTokenServices 的 refreshAccessToken 方法
     *
     * @param refreshToken 刷新令牌
     * @return 访问令牌的信息
     */
    RefreshTokenDo refreshToken(String refreshToken);

    /**
     * 移除访问令牌
     * 注意：该流程中，会移除相关的刷新令牌
     * <p>
     * 参考 DefaultTokenServices 的 revokeToken 方法
     *
     * @param accessToken 刷新令牌
     * @return 访问令牌的信息
     */
    RefreshTokenDo removeTokenByRt(String accessToken);


    /**
     * 获得访问令牌分页
     *
     * @param reqVo 请求
     * @return 访问令牌分页
     */
    PageResult<RefreshTokenDo> getAccessTokenPage(TokenPageReqVO reqVo);
    // ========== 角色-菜单的相关方法  ==========

    // ========== 角色-菜单的相关方法  ==========
    void assignRoleMenu(AssignRoleMenuReqVo roleMenuReqVo);

    /**
     * 处理角色删除时，删除关联授权数据
     *
     * @param roleId 角色编号
     */
    void processRoleDeleted(Long roleId);

    /**
     * 处理菜单删除时，删除关联授权数据
     *
     * @param menuId 菜单编号
     */
    void processMenuDeleted(Long menuId);


    /**
     * 获得角色们拥有的菜单编号集合
     *
     * @param authRoleBoCollection 角色编号数组
     * @param rootId
     * @return 菜单编号集合
     */
    Set<SysMenuDo> getMenusByRoles(Collection<AuthRoleBo> authRoleBoCollection, Long rootId);

    Collection<AssignMenu> getAccessMenuByRoleId(Long roleIds);


    // ========== 用户-角色的相关方法  ==========

    /**
     * 设置用户角色
     *
     * @param userId  角色编号
     * @param roleIds 角色编号集合
     */
    void assignUserRole(Long userId, Set<Long> roleIds);

    /**
     * 处理用户删除时，删除关联授权数据
     *
     * @param userId 用户编号
     */
    void processUserDeleted(Long userId);

    /**
     * 获得用户拥有的角色编号集合
     *
     * @param userId 用户编号
     * @return 角色编号集合
     */
    List<Long> getUserRoleIdListByUserId(Long userId);

}
