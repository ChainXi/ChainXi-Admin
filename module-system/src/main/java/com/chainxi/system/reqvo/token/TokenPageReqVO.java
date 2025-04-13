package com.chainxi.system.reqvo.token;

import com.chainxi.common.web.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Schema(description = "管理后台 - 访问令牌分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class TokenPageReqVO extends PageParam {
    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "666")
    private Long userId;

}
