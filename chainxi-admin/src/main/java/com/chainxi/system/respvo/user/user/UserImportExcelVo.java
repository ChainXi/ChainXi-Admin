package com.chainxi.system.respvo.user.user;

import com.alibaba.excel.annotation.ExcelProperty;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.system.annotations.DictFormat;
import com.chainxi.system.constants.system.DictTypeConstants;
import com.chainxi.system.utils.DictExcelConvert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class UserImportExcelVo {

    @ExcelProperty("登录名称")
    private String userName;

    @ExcelProperty("用户名称")
    private String nickName;

    @ExcelProperty("用户邮箱")
    private String email;

    @ExcelProperty("手机号码")
    private String phoneNumber;

    @ExcelProperty(value = "用户性别", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.USER_SEX)
    private Integer sex;

    @ExcelProperty(value = "账号状态", converter = DictExcelConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private CommonStatusEnum status;

}
