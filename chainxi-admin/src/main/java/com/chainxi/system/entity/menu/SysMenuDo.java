package com.chainxi.system.entity.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.chainxi.common.web.pojo.LogicLessBaseDo;
import com.chainxi.system.enums.auth.MenuType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 菜单表(Menu)实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu", autoResultMap = true)
public class SysMenuDo extends LogicLessBaseDo {
    @Serial
    private static final long serialVersionUID = -1L;

    @TableId
    private Long id;
    /**
     * 父节点
     */
    private Long pid;
    /**
     * 根节点
     */
    private Long rootId;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单类型(0menu 1page 2button)
     */
    private MenuType type;
    /**
     * 路由路径
     */
    private String routerPath;
    /**
     * 组件路径
     */
    private String componentPath;
    /**
     * 资源Id
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Long[] resourceIds = new Long[0];
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单图标
     */
    private Boolean keepAlive;
    /**
     * 菜单状态（0正常 1隐藏 2禁用）
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
}
