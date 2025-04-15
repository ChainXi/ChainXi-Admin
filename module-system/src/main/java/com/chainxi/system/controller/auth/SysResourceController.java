package com.chainxi.system.controller.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.convert.auth.SysResourceConvert;
import com.chainxi.system.entity.menu.SysResourceDo;
import com.chainxi.system.reqvo.menu.SysResourcePageReqVo;
import com.chainxi.system.reqvo.menu.SysResourceUpdateReqVo;
import com.chainxi.system.respvo.menu.SysResourceRespVo;
import com.chainxi.system.service.menu.ResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@Tag(name = "管理后台 - 资源管理")
@RestController
@RequestMapping("/sys/resource")
@RequiredArgsConstructor
public class SysResourceController {
    private final ResourceService resourceService;

    @GetMapping("/query")
    public ResponseResult<SysResourceRespVo> getResource(@RequestParam("id") Long id) {
        return ResponseResult.success(SysResourceConvert.INSTANCE.convertToRespVo(resourceService.queryById(
                id)));
    }

    @GetMapping("/query-all")
    public ResponseResult<Collection<SysResourceRespVo>> getResource() {
        return ResponseResult.success(resourceService
                .selectAll()
                .stream()
                .map(SysResourceConvert.INSTANCE::convertToRespVo)
                .collect(Collectors.toList()));
    }

    @GetMapping("/query-page")
    public ResponseResult<PageResult<SysResourceRespVo>> getResourcePage(SysResourcePageReqVo reqVo) {
        PageResult<SysResourceDo> sysResourceDoPageResult = resourceService.selectPage(reqVo);
        return ResponseResult.success(new PageResult<>(sysResourceDoPageResult
                .getList()
                .stream()
                .map(SysResourceConvert.INSTANCE::convertToRespVo)
                .collect(Collectors.toList()), sysResourceDoPageResult.getTotal()));
    }

    @PostMapping("/update")
    public ResponseResult updateResource(@RequestBody SysResourceUpdateReqVo reqVo) {
        resourceService.updateResource(reqVo);
        return ResponseResult.success();
    }

    @PutMapping("/create")
    public ResponseResult createResource(@RequestBody SysResourceUpdateReqVo reqVo) {
        resourceService.createResource(reqVo);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteResourceById(@RequestParam("id") Long id) {
        resourceService.deleteResourceById(id);
        return ResponseResult.success();
    }
}
