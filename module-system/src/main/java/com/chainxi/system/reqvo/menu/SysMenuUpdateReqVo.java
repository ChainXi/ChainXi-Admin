package com.chainxi.system.reqvo.menu;

import com.chainxi.system.enums.auth.MenuType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuUpdateReqVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    private Long id;
    /**
     * 父节点
     */
    private Long pid;
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
     * 权限路径标识
     */
    private Long[] resourceIds;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否缓存
     */
    private Boolean keepAlive;
    /**
     * 菜单状态（0正常 1隐藏）
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
