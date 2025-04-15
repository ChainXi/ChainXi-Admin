package com.chainxi.system.respvo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "管理后台 - 角色信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRespVO {

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "管理员")
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String name;

    @Schema(description = "显示顺序不能为空", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @Schema(description = "备注", example = "我是一个角色")
    private String remark;

    @Schema(description = "角色编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "数据范围，参见 DataScopeEnum 枚举类", requiredMode =
            Schema.RequiredMode.REQUIRED, example = "1")
    private Integer dataScope;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举类", requiredMode =
            Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "角色类型，参见 RoleTypeEnum 枚举类", requiredMode = Schema.RequiredMode.REQUIRED
            , example = "1")
    private Integer type;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "时间戳格式")
    private LocalDateTime createTime;

}
