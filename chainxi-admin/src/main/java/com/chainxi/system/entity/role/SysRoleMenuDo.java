package com.chainxi.system.entity.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.pojo.BaseDo;
import com.chainxi.system.enums.auth.ResourceMappingType;
import lombok.Data;

import java.io.Serial;

/**
 * @TableName sys_role_menu
 */
@TableName(value = "sys_role_menu")
@Data
public class SysRoleMenuDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long menuId;
    /**
     * 映射类型
     */
    private ResourceMappingType mappingType;
/*
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRoleMenuDo other = (SysRoleMenuDo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())
        ) &&
                (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals
                (other.getRoleId())) &&
                (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals
                (other.getMenuId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getIndexMapName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }*/
}