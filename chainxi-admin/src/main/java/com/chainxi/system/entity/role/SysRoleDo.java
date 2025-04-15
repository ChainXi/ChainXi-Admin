package com.chainxi.system.entity.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRoleDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 账号状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 角色排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
}

