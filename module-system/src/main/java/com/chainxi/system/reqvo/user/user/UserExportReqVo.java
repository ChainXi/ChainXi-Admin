package com.chainxi.system.reqvo.user.user;

import com.chainxi.common.web.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户导出 Request VO，参数和 UserPageReqVO 是一致的")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExportReqVo {

    @Schema(description = "用户账号，模糊匹配")
    private String userName;

    @Schema(description = "手机号码，模糊匹配")
    private String phoneNumber;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

    @Schema(description = "创建时间", example = "[2022-07-01 00:00:00,2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
