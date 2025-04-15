package com.chainxi.system.controller.auth;

import cn.hutool.crypto.digest.DigestUtil;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.config.captcha.Captcha;
import com.chainxi.system.convert.auth.CaptchaConvert;
import com.chainxi.system.convert.auth.SysAuthConvert;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.memorystore.oauth.OAuthServerStore;
import com.chainxi.system.reqvo.auth.*;
import com.chainxi.system.respvo.auth.CaptchaRespVo;
import com.chainxi.system.respvo.auth.TokenAccessRespVo;
import com.chainxi.system.respvo.auth.TokenRARespVo;
import com.chainxi.system.service.auth.AuthService;
import com.chainxi.system.service.auth.CaptchaService;
import com.chainxi.system.service.user.SysUserService;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping("/sys/auth")
@Slf4j
@RequiredArgsConstructor
public class SysAuthController {
    private final AuthService authService;
    private final SysUserService userService;
    private final CaptchaService captchaService;
    private final OAuthServerStore oAuthServerStore;

    @GetMapping("/authorize")
    public ResponseResult auth(@CookieValue("did") Long did,
            @RequestParam("redirect") String redirect, @RequestParam("clientId") Long clientId) {
        String url = authService.auth(did, redirect, clientId);
        if (url != null) {
            return ResponseResult.success(url);
        } else {
            return ResponseResult.error();
        }
    }

    @PostMapping("/code")
    @PermitAll
    public ResponseResult applyCode(@RequestBody OAuthCertificateReqVo reqVo) {
        TokenRARespVo raRespVo = authService.applyCode(reqVo);
        if (raRespVo != null) {
            return ResponseResult.success(raRespVo);
        } else {
            return ResponseResult.error();
        }
    }

    @PermitAll
    @PostMapping("/login")
    public ResponseResult login(@CookieValue("did") Long did, @RequestBody AuthLoginReqVo authLoginReqVo,
            HttpServletResponse response) {
        PublicKey publicKey = authService
                .queryKeyPair()
                .getPublic();
        if (publicKey == null) {
            return ResponseResult
                    .error()
                    .setMsg("登录失败");
        }
        if (!Objects.equals(authLoginReqVo.getTrace(), DigestUtil.md5Hex(publicKey.getEncoded()))) {
            return ResponseResult
                    .error()
                    .setMsg("登录失败");
        }
        TokenRARespVo login = authService.login(authLoginReqVo,did);
        if (login == null) {
            return ResponseResult
                    .error()
                    .setMsg("登录失败");
        }
        Cookie cookie = new Cookie("uid", login
                .getUserId()
                .toString());
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseResult
                .success(login)
                .setMsg("登录成功");
    }

    @PostMapping("/refresh-token")
    @PermitAll
    @Operation(summary = "刷新令牌")
    @Parameter(name = "refreshToken", description = "刷新令牌", required = true)
    public ResponseResult<TokenAccessRespVo> refreshToken(@RequestParam("rt") String refreshToken) {
        return ResponseResult.success(SysAuthConvert.INSTANCE.convertAT(authService.refreshToken(refreshToken)));
    }

    @PermitAll
    @GetMapping("/query-pub-key")
    public ResponseResult<byte[]> queryPubKey() {
        return ResponseResult.success(authService
                .queryKeyPair()
                .getPublic()
                .getEncoded());
    }

    @PermitAll
    @GetMapping("/query-captcha")
    public ResponseResult<CaptchaRespVo> queryCaptcha() throws IOException {
        Captcha captcha = captchaService.createCaptcha();
        return ResponseResult.success(CaptchaConvert.INSTANCE.convert(captcha));
    }

    @PostMapping("/logout")
    public ResponseResult logout(@RequestHeader("access_token") String token) {
        if (!authService.logout(token)) {
            return ResponseResult
                    .error()
                    .setMsg("登出失败");
        }
        return ResponseResult
                .success()
                .setMsg("登出成功");
    }

    @GetMapping("/query")
    public ResponseResult getAuthInfo(@RequestParam("rootId") Long rootId) {
        AuthUserBo loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            SysUserDo sysUserDo = userService.getUser(loginUser.getUserId());
            Set<SysMenuDo> menuList = authService.getMenusByRoles(loginUser.getRoles(), rootId);
            return ResponseResult.success(SysAuthConvert.INSTANCE.convert(sysUserDo, menuList));
        } else {
            return ResponseResult.error();
        }
    }

    @Operation(summary = "获得角色拥有的菜单编号")
    @Parameter(name = "roleId", description = "角色编号", required = true)
    @GetMapping("/query-role-menus")
    public ResponseResult<Collection<AssignMenu>> getRoleMenuList(@RequestParam("roleId") Long roleId) {
        return ResponseResult.success(authService.getAccessMenuByRoleId(roleId));
    }

    @PostMapping("/assign-role-menu")
    @Operation(summary = "赋予角色菜单")
    public ResponseResult<Boolean> assignRoleMenu(@Validated @RequestBody AssignRoleMenuReqVo reqVo) {
        authService.assignRoleMenu(reqVo);
        return ResponseResult.success(true);
    }

    @Operation(summary = "获得管理员拥有的角色编号列表")
    @Parameter(name = "userId", description = "用户编号", required = true)
    @GetMapping("/query-user-roles")
    public ResponseResult<List<Long>> listAdminRoles(@RequestParam("userId") Long userId) {
        return ResponseResult.success(authService.getUserRoleIdListByUserId(userId));
    }

    @Operation(summary = "赋予用户角色")
    @PostMapping("/assign-user-role")
    public ResponseResult<Boolean> assignUserRole(@Validated @RequestBody AssignUserRoleReqVo reqVo) {
        authService.assignUserRole(reqVo.getUserId(), reqVo.getRoleIds());
        return ResponseResult.success(true);
    }

}
