package com.chainxi.system.entity.organization;

import com.baomidou.mybatisplus.annotation.*;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.pojo.BaseDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 部门表 sys_dept
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dept")
public class SysDeptDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 部门ID 8*8 level*num
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 负责人
     */
    private Long leaderId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 部门状态:0正常,1停用
     */
    private CommonStatusEnum status;
    /**
     * 是否删除
     */
    @TableField(exist = false)
    private Boolean deleted;


}
