package com.chainxi.system.service.menu;

import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.reqvo.menu.SysMenuListReqVo;
import com.chainxi.system.reqvo.menu.SysMenuUpdateReqVo;

import java.util.List;

public interface SysMenuService {

    List<SysMenuDo> getMenuList(SysMenuListReqVo reqVo);

    SysMenuDo getMenu(Long id);

    Boolean saveMenu(SysMenuUpdateReqVo sysMenuRespVo);

    boolean updateMenu(SysMenuUpdateReqVo sysMenuRespVo);

    void deleteMenu(Long id);

}
