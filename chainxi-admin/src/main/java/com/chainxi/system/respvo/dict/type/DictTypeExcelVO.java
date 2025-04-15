package com.chainxi.system.respvo.dict.type;

import com.alibaba.excel.annotation.ExcelProperty;
import com.chainxi.system.annotations.DictFormat;
import com.chainxi.system.constants.system.DictTypeConstants;
import com.chainxi.system.utils.DictExcelConvert;
import lombok.Data;

/**
 * 字典类型 Excel 导出响应 VO
 */
@Data
public class DictTypeExcelVO {

    @ExcelProperty("字典主键")
    private Long id;

    @ExcelProperty("字典名称")
    private String name;

    @ExcelProperty("字典类型")
    private String type;

    @ExcelProperty(value = "状态", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;

}
