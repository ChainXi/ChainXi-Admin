package com.chainxi.system.controller.auth;

import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.convert.auth.SysMenuConvert;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.reqvo.menu.SysMenuListReqVo;
import com.chainxi.system.reqvo.menu.SysMenuUpdateReqVo;
import com.chainxi.system.respvo.menu.SysMenuRespVo;
import com.chainxi.system.respvo.menu.SysMenuSimpleRespVo;
import com.chainxi.system.service.menu.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
@RequiredArgsConstructor
public class SysMenuController {
    private final SysMenuService sysMenuService;

    @GetMapping("/query")
    public ResponseResult<SysMenuRespVo> getMenu(@RequestParam("id") Long id) {
        return ResponseResult.success(SysMenuConvert.INSTANCE.convertToMenuDto(sysMenuService.getMenu(
                id)));
    }

    @GetMapping("/query-list")
    public ResponseResult<List<SysMenuRespVo>> getMenuList(SysMenuListReqVo reqVo) {
        List<SysMenuRespVo> menuList =
                SysMenuConvert.INSTANCE.convertToMenuDtos(sysMenuService.getMenuList(reqVo));
        return ResponseResult.success(menuList);
    }

    @GetMapping("/query-index-map")
    @Operation(summary = "获取菜单精简信息列表", description =
            "只包含被开启的菜单，用于【角色分配菜单】功能的选项。" +
                    "在多租户的场景下，会只返回租户所在套餐有的菜单")
    public ResponseResult<List<SysMenuSimpleRespVo>> getIndexMapMenuList() {
        SysMenuListReqVo sysMenuListReqVo = new SysMenuListReqVo();
        List<SysMenuDo> list = sysMenuService.getMenuList(sysMenuListReqVo);
        list.sort(Comparator.comparing(SysMenuDo::getSort));
        return ResponseResult.success(SysMenuConvert.INSTANCE.convertToSimpleList(list));
    }


    @PostMapping("/create")
    public ResponseResult saveMenus(@RequestBody SysMenuUpdateReqVo sysMenuUpdateReqVo) {
        sysMenuService.saveMenu(sysMenuUpdateReqVo);
        return ResponseResult.success();
    }

    @PutMapping("/update")
    public ResponseResult updateMenu(@RequestBody SysMenuUpdateReqVo sysMenuUpdateReqVo) {
        sysMenuService.updateMenu(sysMenuUpdateReqVo);
        return ResponseResult.success();
    }


    @DeleteMapping("/delete")
    public ResponseResult deleteMenu(@RequestParam("id") Long id) {
        sysMenuService.deleteMenu(id);
        return ResponseResult.success();
    }

}
