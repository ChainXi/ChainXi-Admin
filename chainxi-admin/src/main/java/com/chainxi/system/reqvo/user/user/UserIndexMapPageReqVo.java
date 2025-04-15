package com.chainxi.system.reqvo.user.user;

import com.chainxi.common.web.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Schema(description = "管理后台 - 用户分页 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIndexMapPageReqVo extends PageParam {
    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "用户账号，模糊匹配")
    private String name;

}
