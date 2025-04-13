package com.chainxi.system.reqvo.user.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 用户个人中心更新密码 Request VO")
@Data
public class UserProfileUpdatePasswordReqVo {

    @Schema(description = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;

    @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "654321")
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;


    @Schema(description = "校验码", requiredMode = Schema.RequiredMode.REQUIRED, example = "654321")
    @NotEmpty(message = "页面超时")
    private String trace;

}
