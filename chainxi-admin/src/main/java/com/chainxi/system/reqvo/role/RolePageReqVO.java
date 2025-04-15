package com.chainxi.system.reqvo.role;

import com.chainxi.common.web.pojo.PageParam;
import com.chainxi.common.web.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 角色分页 Request VO")
@Data
public class RolePageReqVO extends PageParam {

    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "角色名称，模糊匹配")
    private String name;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

    @Schema(description = "创建时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
