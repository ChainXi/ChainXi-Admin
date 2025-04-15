package com.chainxi.system.respvo.dict.type;

import com.chainxi.common.web.pojo.PageParam;
import com.chainxi.common.web.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 字典类型分页列表 Request VO")
@Data
public class DictTypePageReqVo extends PageParam {

    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "字典类型名称，模糊匹配")
    private String name;

    @Schema(description = "字典类型，模糊匹配", example = "sys_common_sex")
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String type;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}
