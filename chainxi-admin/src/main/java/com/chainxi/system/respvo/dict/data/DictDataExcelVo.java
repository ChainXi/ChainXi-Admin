package com.chainxi.system.respvo.dict.data;


import com.alibaba.excel.annotation.ExcelProperty;
import com.chainxi.system.annotations.DictFormat;
import com.chainxi.system.constants.system.DictTypeConstants;
import com.chainxi.system.utils.DictExcelConvert;
import lombok.Data;

/**
 * 字典数据 Excel 导出响应 VO
 */
@Data
public class DictDataExcelVo {

    @ExcelProperty("字典编码")
    private Long id;

    @ExcelProperty("字典排序")
    private Integer sort;

    @ExcelProperty("字典标签")
    private String label;

    @ExcelProperty("字典键值")
    private String value;

    @ExcelProperty("字典类型")
    private String dictType;

    @ExcelProperty(value = "状态", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;

}
