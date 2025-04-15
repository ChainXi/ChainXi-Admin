package com.chainxi.system.reqvo.user.user;

import com.chainxi.common.web.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 用户更新 Request VO")
@Data
public class UserUpdateReqVo {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "用户编号不能为空")
    private Long id;

    @Schema(description = "用户账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户账号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、字母 组成")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String userName;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickName;


    @Schema(description = "部门")
    private Long deptId;

    @Schema(description = "用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    @Schema(description = "手机号码", example = "15601691300")
    @Mobile
    private String phoneNumber;

    @Schema(description = "用户性别，参见 SexEnum 枚举类", example = "1")
    private Integer sex;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "备注", example = "我是一个用户")
    private String remark;
}
