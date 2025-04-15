package com.chainxi.system.controller.auth;

import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.common.web.utils.ExcelUtils;
import com.chainxi.system.convert.auth.SysRoleConvert;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.reqvo.role.*;
import com.chainxi.system.respvo.role.RoleExcelVO;
import com.chainxi.system.respvo.role.RoleRespVO;
import com.chainxi.system.respvo.role.RoleSimpleRespVO;
import com.chainxi.system.service.role.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.singleton;

@RestController
@RequestMapping("/sys/role")
@RequiredArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;

    @GetMapping("/query")
    @Operation(summary = "获得角色信息")
    public ResponseResult<RoleRespVO> getRole(@RequestParam("id") Long id) {
        SysRoleDo role = sysRoleService.getRole(id);
        return ResponseResult.success(SysRoleConvert.INSTANCE.convert(role));
    }

    @GetMapping("/query-page")
    @Operation(summary = "获得角色分页")
    public ResponseResult<PageResult<SysRoleDo>> getRolePage(RolePageReqVO reqVo) {
        return ResponseResult.success(sysRoleService.getRolePage(reqVo));
    }

    @GetMapping("/query-index-map")
    @Operation(summary = "获取角色精简信息列表", description = "只包含被开启的角色，主要用于前端的下拉选项")
    public ResponseResult<List<RoleSimpleRespVO>> getIndexMapRoleList() {
        // 获得角色列表，只要开启状态的
        List<SysRoleDo> list =
                sysRoleService.getRoleListByStatus(singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 排序后，返回给前端
        list.sort(Comparator.comparing(SysRoleDo::getSort));
        return ResponseResult.success(SysRoleConvert.INSTANCE.convertList02(list));
    }

    @PostMapping("/create")
    @Operation(summary = "创建角色")
    public ResponseResult<Long> createRole(@Valid @RequestBody RoleCreateReqVo reqVo) {
        return ResponseResult.success(sysRoleService.createRole(reqVo, null));
    }

    @PutMapping("/update")
    @Operation(summary = "修改角色")
    public ResponseResult<Boolean> updateRole(@Valid @RequestBody RoleUpdateReqVO reqVo) {
        sysRoleService.updateRole(reqVo);
        return ResponseResult.success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "修改角色状态")
    public ResponseResult<Boolean> updateRoleStatus(@Valid @RequestBody RoleUpdateStatusReqVO reqVo) {
        sysRoleService.updateRoleStatus(reqVo.getId(), reqVo.getStatus());
        return ResponseResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除角色")
    @Parameter(name = "id", description = "角色编号", required = true, example = "1024")
    public ResponseResult<Boolean> deleteRole(@RequestParam("id") Long id) {
        sysRoleService.deleteRole(id);
        return ResponseResult.success(true);
    }

    @GetMapping("/export")
//    @OperateLog(type = EXPORT)
    public void export(HttpServletResponse response,
            @Validated RoleExportReqVO reqVo) throws IOException {
        List<SysRoleDo> list = sysRoleService.getRoleList(reqVo);
        List<RoleExcelVO> data = SysRoleConvert.INSTANCE.convertList03(list);
        // 输出
        ExcelUtils.write(response, "角色数据.xls", "角色列表", RoleExcelVO.class, data);
    }
}
