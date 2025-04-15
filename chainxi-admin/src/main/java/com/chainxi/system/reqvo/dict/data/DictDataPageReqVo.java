package com.chainxi.system.reqvo.dict.data;

import com.chainxi.common.web.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Size;

import java.io.Serial;

@Schema(description = "管理后台 - 字典类型分页列表 Request VO")
@Data
public class DictDataPageReqVo extends PageParam {

    @Serial
    private static final long serialVersionUID = -1L;
    @Schema(description = "字典标签")
    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;

    @Schema(description = "字典类型，模糊匹配", example = "sys_common_sex")
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

}
