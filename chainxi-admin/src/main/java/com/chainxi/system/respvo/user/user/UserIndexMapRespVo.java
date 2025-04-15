package com.chainxi.system.respvo.user.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 用户精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIndexMapRespVo {

    @Schema(description = "用户编号")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户昵称")
    private String nickName;

}
