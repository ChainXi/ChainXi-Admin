package com.chainxi.system.controller.organization;

import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.convert.organization.SysDeptConvert;
import com.chainxi.system.reqvo.organization.DeptCreateReqVo;
import com.chainxi.system.reqvo.organization.DeptQueryReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateStatusReqVo;
import com.chainxi.system.respvo.organization.SysDeptDetailRespVo;
import com.chainxi.system.respvo.organization.SysDeptMapRespVo;
import com.chainxi.system.respvo.organization.SysDeptRespVo;
import com.chainxi.system.service.organization.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor
@Slf4j
public class SysDeptController {
    private final SysDeptService sysDeptService;


    @GetMapping("/query")
    @Operation(summary = "获得部门详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult<SysDeptDetailRespVo> getDeptDetail(@RequestParam("id") Long id) {
        return ResponseResult.success(sysDeptService.selectOneByDeptId(id));
    }

    @GetMapping("/query-all")
    public ResponseResult<List<SysDeptRespVo>> queryAllDept(DeptQueryReqVo reqVo) {
        return ResponseResult.success(sysDeptService.selectList(reqVo));
    }

    @GetMapping("/query-index-map")
    @Operation(summary = "获取部门精简信息列表", description = "只包含被开启的部门，主要用于前端的下拉选项")
    public ResponseResult<List<SysDeptMapRespVo>> getIndexMapDeptList() {
        return ResponseResult.success(sysDeptService
                .selectMapList()
                .stream()
                .map(SysDeptConvert.INSTANCE::convertDo2MapVo)
                .collect(Collectors.toList()));
    }

    @PostMapping("/create")
    @Operation(summary = "新增部门")
    public ResponseResult createDept(@RequestBody DeptCreateReqVo reqVo) {
        if (sysDeptService.createDept(reqVo)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @PutMapping("/update")
    @Operation(summary = "修改部门")
    public ResponseResult updateDept(@Valid @RequestBody DeptUpdateReqVo reqVo) {
        if (sysDeptService.updateDept(reqVo)) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @PutMapping("/update-status")
    @Operation(summary = "修改部门状态")
    public ResponseResult updateDeptStatus(@Valid @RequestBody DeptUpdateStatusReqVo reqVo) {
        Integer updateCount = sysDeptService.updateDeptStatus(reqVo);
        if (updateCount > 0) {
            return ResponseResult.success(updateCount);
        } else {
            return ResponseResult.error();
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除部门")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult deleteDept(@RequestParam("id") Long id) {
        return ResponseResult.success(sysDeptService.deleteDept(id));
    }

}
