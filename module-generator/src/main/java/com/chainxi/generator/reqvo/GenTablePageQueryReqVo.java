package com.chainxi.generator.reqvo;

import com.chainxi.common.web.pojo.PageParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author : ChainXi
 * @Date : 2025/3/2 17:14
 * @Desc :
 */
@Data
public class GenTablePageQueryReqVo extends PageParam {
    /** 表名称 */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /** 表描述 */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;
}
