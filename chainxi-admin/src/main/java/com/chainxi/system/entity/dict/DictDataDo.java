package com.chainxi.system.entity.dict;

import com.baomidou.mybatisplus.annotation.*;
import com.chainxi.common.web.pojo.BaseDo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 字典数据表
 *
 * @author chainxi
 */
@TableName("sys_dict_data")
@KeySequence("system_dict_data_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictDataDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 字典数据编号
     */
    @TableId
    private Long id;
    /**
     * 字典排序
     */
    private Integer sort;
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
     * <p>
     * 冗余 {@link DictDataDo#getDictType()}
     */
    private String dictType;
    /**
     * 状态
     * <p>
     * 枚举 {}
     */
    private Integer status;
    /**
     * 颜色类型
     * <p>
     * 对应到 element-ui 为 default、primary、success、info、warning、danger
     */
    private String colorType;
    /**
     * css 样式
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String cssClass;
    /**
     * 备注
     */
    private String remark;

}
