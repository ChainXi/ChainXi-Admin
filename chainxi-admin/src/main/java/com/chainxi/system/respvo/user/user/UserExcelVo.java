package com.chainxi.system.respvo.user.user;


import com.alibaba.excel.annotation.ExcelProperty;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.system.utils.DictExcelConvert;
import com.chainxi.system.annotations.DictFormat;
import com.chainxi.system.constants.system.DictTypeConstants;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 Excel 导出 VO
 */
@Data
public class UserExcelVo {

    @ExcelProperty("用户编号")
    private Long id;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("用户昵称")
    private String nickName;
    /**
     * 部门Id
     */
    @ExcelProperty("部门Id")
    private Long deptId;

    @ExcelProperty("用户邮箱")
    private String email;

    @ExcelProperty("手机号码")
    private String phoneNumber;

    @ExcelProperty(value = "用户性别", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.USER_SEX)
    private Integer sex;

    @ExcelProperty(value = "帐号状态", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private CommonStatusEnum status;

    @ExcelProperty("最后登录IP")
    private String loginIp;

    @ExcelProperty("最后登录时间")
    private LocalDateTime loginDate;


}
