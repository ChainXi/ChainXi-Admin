package com.chainxi.system.respvo.dict.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户 App - 字典数据信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDictDataRespVo {

    @Schema(description = "字典数据编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED)
    private String label;

    @Schema(description = "字典值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example =
            "sys_common_sex")
    private String dictType;

}
