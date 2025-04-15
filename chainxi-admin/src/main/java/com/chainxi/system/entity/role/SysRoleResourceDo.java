package com.chainxi.system.entity.role;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import lombok.Data;

import java.io.Serial;

@Data
@TableName(value = "sys_role_resource")
public class SysRoleResourceDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    @TableId
    private Long id;
    private Long roleId;
    private Long resourceId;
}