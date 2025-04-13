package com.chainxi.system.controller.user;

import cn.hutool.crypto.digest.DigestUtil;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.convert.user.SysUserConvert;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdatePasswordReqVo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdateReqVo;
import com.chainxi.system.respvo.user.profile.UserProfileRespVO;
import com.chainxi.system.service.auth.AuthService;
import com.chainxi.system.service.role.SysRoleService;
import com.chainxi.system.service.user.SysUserService;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;

@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/sys/user/profile")
@Validated
@Slf4j
@RequiredArgsConstructor
public class SysUserProfileController {
    private final SysUserService userService;
    private final AuthService authService;
    private final SysRoleService roleService;

    @GetMapping("/get")
    @Operation(summary = "获得登录用户信息")
    public ResponseResult<UserProfileRespVO> getUserProfile() {
        // 获得用户基本信息
        Long userId = Objects
                .requireNonNull(SecurityFrameworkUtils.getLoginUser())
                .getUserId();
        SysUserDo user = userService.getUser(userId);
        // 获得用户角色
        List<SysRoleDo> userRoles =
                roleService.getRoleListFromCache(authService.getUserRoleIdListByUserId(user.getId()));


        return ResponseResult.success(SysUserConvert.INSTANCE.convert(userRoles, user));
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户个人信息")
    public ResponseResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVo reqVo) {
        userService.updateUserProfile(Objects
                .requireNonNull(SecurityFrameworkUtils.getLoginUser())
                .getUserId(), reqVo);
        return ResponseResult.success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户个人密码")
    public ResponseResult updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVo reqVo) {
        PublicKey publicKey = authService
                .queryKeyPair()
                .getPublic();
        if (publicKey == null) {
            return ResponseResult
                    .error()
                    .setMsg("修改失败");
        }
        if (!Objects.equals(reqVo.getTrace(), DigestUtil.md5Hex(publicKey.getEncoded()))) {
            return ResponseResult
                    .error()
                    .setMsg("修改失败");
        }
        userService.updateUserPassword(Objects
                .requireNonNull(SecurityFrameworkUtils.getLoginUser())
                .getUserId(), reqVo);
        return ResponseResult.success();
    }

    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT})
    // 解决 uni-app 不支持 Put 上传文件的问题
    @Operation(summary = "上传用户个人头像")
    public ResponseResult updateUserAvatar(@RequestBody String avatar) {
        if (userService.updateUserAvatar(Objects
                .requireNonNull(SecurityFrameworkUtils.getLoginUser())
                .getUserId(), avatar)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

}
