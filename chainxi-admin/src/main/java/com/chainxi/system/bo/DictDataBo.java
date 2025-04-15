package com.chainxi.system.bo;

import com.chainxi.common.web.constant.CommonStatusEnum;
import lombok.Data;


@Data
public class DictDataBo {

    /**
     * 字典标签
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
