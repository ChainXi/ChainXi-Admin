package com.chainxi.system.respvo.role;

import com.alibaba.excel.annotation.ExcelProperty;
import com.chainxi.system.annotations.DictFormat;
import com.chainxi.system.constants.system.DictTypeConstants;
import com.chainxi.system.utils.DictExcelConvert;
import lombok.Data;

/**
 * 角色 Excel 导出响应 VO
 */
@Data
public class RoleExcelVO {

    @ExcelProperty("角色序号")
    private Long id;

    @ExcelProperty("角色名称")
    private String name;

    @ExcelProperty("角色排序")
    private Integer sort;

    @ExcelProperty("数据范围")
    private Integer dataScope;

    @ExcelProperty(value = "角色状态", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private String status;

}
