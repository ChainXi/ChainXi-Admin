package com.chainxi.system.service.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.chainxi.common.web.component.ContextLoader;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.common.web.exception.enums.GlobalErrorCodeConstants;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.utils.DateUtils;
import com.chainxi.common.web.utils.EncryptUtils;
import com.chainxi.system.bo.AuthRoleBo;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.constants.system.DefaultValueConstants;
import com.chainxi.system.convert.auth.SysAuthConvert;
import com.chainxi.system.convert.auth.SysMenuConvert;
import com.chainxi.system.convert.auth.SysRoleConvert;
import com.chainxi.system.convert.user.SysUserConvert;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.entity.role.SysRoleMenuDo;
import com.chainxi.system.entity.role.SysRoleResourceDo;
import com.chainxi.system.entity.token.OAuth2ClientDo;
import com.chainxi.system.entity.token.RefreshTokenDo;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.entity.user.SysUserRoleDo;
import com.chainxi.system.enums.auth.MenuType;
import com.chainxi.system.enums.auth.ResourceMappingType;
import com.chainxi.system.mapper.menu.SysMenuMapper;
import com.chainxi.system.mapper.role.SysRoleMenuMapper;
import com.chainxi.system.mapper.role.SysRoleResourceMapper;
import com.chainxi.system.mapper.token.OAuth2ClientMapper;
import com.chainxi.system.mapper.token.RefreshTokenMapper;
import com.chainxi.system.mapper.user.SysUserMapper;
import com.chainxi.system.mapper.user.SysUserRoleMapper;
import com.chainxi.system.memorystore.oauth.OAuthServerStore;
import com.chainxi.system.memorystore.token.TokenStore;
import com.chainxi.system.reqvo.auth.AssignMenu;
import com.chainxi.system.reqvo.auth.AssignRoleMenuReqVo;
import com.chainxi.system.reqvo.auth.AuthLoginReqVo;
import com.chainxi.system.reqvo.auth.OAuthCertificateReqVo;
import com.chainxi.system.reqvo.menu.SysMenuListReqVo;
import com.chainxi.system.reqvo.token.TokenPageReqVO;
import com.chainxi.system.respvo.auth.OAuthClientCertificateRespVo;
import com.chainxi.system.respvo.auth.TokenRARespVo;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import com.chainxi.system.utils.UrlUtils;
import com.ejlchina.okhttps.OkHttps;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyPair;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CaptchaService captchaService;
    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final OAuthServerStore oAuthServerStore;
    private final SysUserMapper sysUserMapper;
    private final SysMenuMapper sysMenuMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;
    private final SysRoleResourceMapper sysRoleResourceMapper;
    private final RefreshTokenMapper refreshTokenMapper;
    private final OAuth2ClientMapper oAuth2ClientMapper;

    @Nullable
    @Override
    public String auth(Long did, String redirect, Long clientId) {
        AuthUserBo loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            Long code = DefaultIdentifierGenerator
                    .getInstance()
                    .nextId(null);
            oAuthServerStore.putCode(code, new OAuthClientCertificateRespVo()
                    .setDid(did)
                    .setUserId(loginUser.getUserId())
                    .setClientId(clientId));
            return UrlUtils.joinParam(redirect, "code", code);
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public TokenRARespVo applyCode(OAuthCertificateReqVo reqVo) {
        Long code = reqVo.getCode();
        OAuthClientCertificateRespVo certificateByCode =
                oAuthServerStore.getCertificateByCode(code);
        if (certificateByCode == null) {
            return null;
        }
        oAuthServerStore.removeCode(code);
        if (!certificateByCode
                .getClientId()
                .equals(reqVo.getClientId())) {
            return null;
        }
        OAuth2ClientDo clientDo = oAuth2ClientMapper.selectByClientId(reqVo.getClientId());
        if (!clientDo
                .getSecret()
                .equals(reqVo.getClientSecret())) {
            return null;
        }
        RefreshTokenDo raToken =
                createRAToken(certificateByCode.getClientId(), certificateByCode.getUserId(),
                        certificateByCode.getDid());
        tokenStore.setAccessToken(raToken.getAccessToken(), new AuthUserBo()
                //TODO TBD 是否需要?
                // .setRoles()
                // .setDeptIds()
                .setUserId(certificateByCode.getUserId())
                .setClientId(certificateByCode.getClientId()));
        return SysAuthConvert.INSTANCE.convertRAT(raToken);
    }

    @Nullable
    @Override
    public TokenRARespVo login(AuthLoginReqVo authLoginReqVo, Long did) {
        if (captchaService.getCaptchaEnabled()) {
            if (!captchaService.consumeCaptcha(authLoginReqVo.getCaptcha(),
                    authLoginReqVo.getUuid())) {
                return null;
            }
        }
        String rawPassword = new String(EncryptUtils.decrypt(ContextLoader
                .getBean(getClass())
                .queryKeyPair()
                .getPrivate(), Base64
                .getDecoder()
                .decode(authLoginReqVo.getPassword())));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authLoginReqVo.getUserName(), rawPassword);
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return null;
        }
        AuthUserBo authUserBo = (AuthUserBo) (authenticate.getPrincipal());

        RefreshTokenDo raToken = createRAToken(0L, authUserBo.getUserId(), did);
        tokenStore.setAccessToken(raToken.getAccessToken(), authUserBo);

        return SysAuthConvert.INSTANCE.convertRAT(raToken);
    }

    @Override
    public Boolean logout(String uid, String accessToken) {
        RefreshTokenDo refreshTokenDo = refreshTokenMapper.selectByAccessToken(accessToken);
        Long userId = refreshTokenDo.getUserId();
        if (Long.parseLong(uid) != userId) {
            throw new BizException(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        Long did = refreshTokenDo.getDid();
        Long clientId = refreshTokenDo.getClientId();
        List<RefreshTokenDo> clientTokens = refreshTokenMapper
                .selectClient(userId, did)
                .stream()
                .toList();
        Map<Long, String> logoutCallUrls = oAuth2ClientMapper
                .selectLogoutCallUrls(clientTokens
                        .stream()
                        .map(RefreshTokenDo::getClientId)
                        .toList())
                .stream()
                .collect(Collectors.toMap(OAuth2ClientDo::getClientId,
                        OAuth2ClientDo::getLogoutCall));
        refreshTokenMapper.deleteByRefreshToken(clientTokens
                .stream()
                .filter(ct -> logoutCallUrls.containsKey(ct.getClientId()))
                .map(RefreshTokenDo::getRefreshToken)
                .toList());
        clientTokens
                .stream()
                .map(RefreshTokenDo::getAccessToken)
                .filter(Objects::nonNull)
                .forEach(tokenStore::removeAccessToken);
        clientTokens
                .stream()
                .filter(e -> !Objects.equals(e.getClientId(), clientId))
                .forEach(ct -> {
                    if (ct.getClientId() != 0L) {
                        OkHttps
                                .async(logoutCallUrls.get(ct.getClientId()))
                                .addHeader("rt", ct.getRefreshToken())
                                .post();
                    }
                });
        return Boolean.TRUE;
    }

    @Override
    @Cacheable(value = CacheKeyConstants.ENCRYPT)
    public KeyPair queryKeyPair() {
        return EncryptUtils.generateRsaPair();
    }


    @Override
    @Transactional
    public RefreshTokenDo createRAToken(Long clientId, Long userId, Long did) {
        RefreshTokenDo refreshToken = new RefreshTokenDo()
                .setUserId(userId)
                .setClientId(clientId)
                .setDid(did)
                .setRefreshToken(generateRefreshToken())
                .setExpiresTime(LocalDateTime
                        .now()
                        .plusSeconds(DefaultValueConstants.CONFIG_AUTH_USER_RT_EXPIRE));
        // 创建访问令牌
        setOAuth2AccessToken(refreshToken);
        refreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    @Override
    @Transactional
    public Integer removeAuthUser(Long userId) {
        List<RefreshTokenDo> refreshTokenDos = refreshTokenMapper.selectAuthUser((userId));
        refreshTokenDos.forEach(e -> tokenStore.removeAccessToken(e.getAccessToken()));
        return refreshTokenMapper.removeAuthUser(userId);
    }

    @Override
    public RefreshTokenDo refreshToken(String uid, String refreshToken) {
        // 查询访问令牌
        RefreshTokenDo refreshTokenDo = refreshTokenMapper.selectByRefreshToken(refreshToken);
        if (refreshTokenDo == null) {
            throw new BizException(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        if(refreshTokenDo.getUserId() != Long.parseLong(uid)){
            throw new BizException(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        tokenStore.removeAccessToken(refreshTokenDo.getAccessToken());

        // 已过期的情况下，删除刷新令牌
        if (DateUtils.isExpired(refreshTokenDo.getExpiresTime())) {
            refreshTokenMapper.deleteById(refreshTokenDo.getId());
            throw new BizException(GlobalErrorCodeConstants.UNAUTHORIZED);
        }

        // 创建访问令牌
        setOAuth2AccessToken(refreshTokenDo);
        refreshTokenMapper.updateById(refreshTokenDo);

        SysUserDo sysUserDo = sysUserMapper.selectById(refreshTokenDo.getUserId());
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(sysUserDo)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<AuthRoleBo> rolesByUserId =
                SysRoleConvert.INSTANCE.convertToAuthRole(sysUserRoleMapper.getRolesByUserId((sysUserDo.getId())));

        tokenStore.setAccessToken(refreshTokenDo.getAccessToken(),
                SysUserConvert.INSTANCE.convert(sysUserDo, rolesByUserId));
        return refreshTokenDo;
    }

    @Override
    public RefreshTokenDo removeTokenByRt(String refreshToken) {
        // 删除访问令牌
        RefreshTokenDo refreshTokenDo = refreshTokenMapper.selectByRefreshToken(refreshToken);
        if (refreshTokenDo == null) {
            return null;
        }
        tokenStore.removeAccessToken(refreshTokenDo.getAccessToken());
        refreshTokenMapper.deleteByRefreshToken(refreshTokenDo.getRefreshToken());
        return refreshTokenDo;
    }

    @Override
    public PageResult<RefreshTokenDo> getAccessTokenPage(TokenPageReqVO reqVo) {
        return refreshTokenMapper.selectPage(reqVo);
    }

    private void setOAuth2AccessToken(RefreshTokenDo refreshTokenDO) {
        refreshTokenDO
                .setAccessToken(generateAccessToken())
                .setAtExpiresTime(LocalDateTime
                        .now()
                        .plusSeconds(DefaultValueConstants.CONFIG_AUTH_USER_AT_EXPIRE));
    }

    private String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }


    // ========== 角色-菜单的相关方法  ==========
    @Override
    public void assignRoleMenu(AssignRoleMenuReqVo roleMenuReqVo) {
        Long roleId = roleMenuReqVo.getRoleId();
        Map<Long, SysMenuDo> sysMenuDoMap = sysMenuMapper
                .selectListByMenuIds(roleMenuReqVo
                        .getMenuIds()
                        .stream()
                        .map(AssignMenu::getMenuId)
                        .collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(SysMenuDo::getId, Function.identity()));
        List<SysRoleMenuDo> roleMenuDos = roleMenuReqVo
                .getMenuIds()
                .stream()
                .map(e -> new SysRoleMenuDo()
                        .setRoleId(roleId)
                        .setMenuId(e.getMenuId())
                        .setMappingType(e.getMappingType()))
                .collect(Collectors.toList());
        List<SysRoleResourceDo> resourceDoList = roleMenuDos
                .stream()
                .filter(e -> sysMenuDoMap
                        .get(e.getMenuId())
                        .getType()
                        .equals(MenuType.BUTTON) || e
                        .getMappingType()
                        .equals(ResourceMappingType.BUCKET))
                .map(e -> sysMenuDoMap
                        .get(e.getMenuId())
                        .getResourceIds())
                .flatMap(Arrays::stream)
                .distinct()
                .map(e -> new SysRoleResourceDo()
                        .setRoleId(roleId)
                        .setResourceId(e))
                .collect(Collectors.toList());

        sysRoleMenuMapper.deleteListByRoleId(roleId);
        sysRoleMenuMapper.insertBatch(roleMenuDos);
        sysRoleResourceMapper.deleteByRoleId(roleId);
        sysRoleResourceMapper.insertBatch(resourceDoList);
    }

    @Override
    public Set<SysMenuDo> getMenusByRoles(Collection<AuthRoleBo> authRoleBos, Long rootId) {
        List<SysMenuDo> sysMenuDos =
                sysMenuMapper.selectList(new SysMenuListReqVo().setRootId(rootId));
        List<Long> roleIds = authRoleBos
                .stream()
                .map(AuthRoleBo::getId)
                .toList();
        List<SysRoleMenuDo> directRoleMenus = sysRoleMenuMapper
                .selectList()
                .stream()
                .filter(e -> roleIds.contains(e.getRoleId()))
                .toList();

        Map<Long, SysMenuDo> sysMenuDoMap = sysMenuDos
                .stream()
                .collect(Collectors.toMap(SysMenuDo::getId, Function.identity()));
        Set<SysMenuDo> result = directRoleMenus
                .stream()
                .map(e -> sysMenuDoMap.get(e.getMenuId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Long> bucketMenuIds = directRoleMenus
                .stream()
                .filter(e -> ResourceMappingType.BUCKET.equals(e.getMappingType()))
                .map(SysRoleMenuDo::getMenuId)
                .collect(Collectors.toList());
        while (!bucketMenuIds.isEmpty()) {
            List<Long> finalBucketMenuIds = bucketMenuIds;
            List<SysMenuDo> bucketChildNodes = sysMenuDos
                    .stream()
                    .filter(e -> finalBucketMenuIds.contains(e.getPid()))
                    .toList();
            result.addAll(bucketChildNodes);
            bucketMenuIds = bucketChildNodes
                    .stream()
                    .map(SysMenuDo::getId)
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public Collection<AssignMenu> getAccessMenuByRoleId(Long roleId) {
        return sysRoleMenuMapper
                .getMenuIdsByRoleId(Collections.singleton(roleId))
                .stream()
                .map(SysMenuConvert.INSTANCE::roleMenuDoToAssignMenu)
                .distinct()
                .collect(Collectors.toList());
    }

    // ========== 用户-角色的相关方法  ==========

    /**
     * 设置用户角色
     *
     * @param userId  角色编号
     * @param roleIds 角色编号集合
     */
    public void assignUserRole(Long userId, Set<Long> roleIds) {
        // 获得角色拥有角色编号
        Set<Long> dbRoleIds = sysUserRoleMapper
                .selectListByUserId(userId)
                .stream()
                .map(SysUserRoleDo::getRoleId)
                .collect(Collectors.toSet());
        // 计算新增和删除的角色编号
        Collection<Long> createRoleIds = CollUtil.subtract(roleIds, dbRoleIds);
        Collection<Long> deleteMenuIds = CollUtil.subtract(dbRoleIds, roleIds);
        // 执行新增和删除。对于已经授权的角色，不用做任何处理
        if (!CollectionUtil.isEmpty(createRoleIds)) {
            sysUserRoleMapper.insertBatch(createRoleIds
                    .stream()
                    .map(roleId -> {
                        SysUserRoleDo entity = new SysUserRoleDo();
                        entity.setUserId(userId);
                        entity.setRoleId(roleId);
                        return entity;
                    })
                    .collect(Collectors.toList()));
        }
        if (!CollectionUtil.isEmpty(deleteMenuIds)) {
            sysUserRoleMapper.deleteListByUserIdAndRoleIdIds(userId, deleteMenuIds);
        }
    }

    /**
     * 获得用户拥有的角色编号集合
     *
     * @param userId 用户编号
     * @return 角色编号集合
     */
    public List<Long> getUserRoleIdListByUserId(Long userId) {
        return sysUserRoleMapper.getRoleIdsByUserId(userId);
    }

    /**
     * 处理角色删除时，删除关联授权数据
     *
     * @param roleId 角色编号
     */
    @Override
    public void processRoleDeleted(Long roleId) {
        // 标记删除 UserRole
        sysUserRoleMapper.deleteListByRoleId(roleId);
        // 标记删除 RoleMenu
        sysRoleMenuMapper.deleteListByRoleId(roleId);
    }

    /**
     * 处理菜单删除时，删除关联授权数据
     *
     * @param menuId 菜单编号
     */
    @Override
    public void processMenuDeleted(Long menuId) {
        sysRoleMenuMapper.deleteListByMenuId(menuId);
    }

    /**
     * 处理用户删除时，删除关联授权数据
     *
     * @param userId 用户编号
     */
    @Override
    public void processUserDeleted(Long userId) {
        sysUserRoleMapper.deleteListByUserId(userId);
        refreshTokenMapper.removeAuthUser(userId);
    }

}
