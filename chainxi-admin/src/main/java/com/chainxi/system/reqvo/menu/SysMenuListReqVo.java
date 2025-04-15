package com.chainxi.system.reqvo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Schema(description = "管理后台 - 菜单列表 Request VO")
@Data
public class SysMenuListReqVo implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "菜单名称，模糊匹配")
    private String name;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

    private Long rootId;

}
