package com.chainxi.system.reqvo.organization;

import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.pojo.PageParam;
import com.chainxi.common.web.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 角色分页 Request VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptQueryReqVo extends PageParam {
    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "部门，模糊匹配")
    private String deptName;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private CommonStatusEnum status;

    @Schema(description = "创建时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
