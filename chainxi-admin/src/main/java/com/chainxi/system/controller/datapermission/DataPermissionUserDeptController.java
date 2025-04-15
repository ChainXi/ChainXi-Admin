package com.chainxi.system.controller.datapermission;

import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.reqvo.datapermission.DataPermissionDeptMatcherUpdateReqVo;
import com.chainxi.system.respvo.datapermission.DataPermissionDeptMatcherRespVo;
import com.chainxi.system.service.datapermission.DataPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "管理后台 - 缓存管理")
@RestController
@RequestMapping("/sys/data-permission-user-dept")
@RequiredArgsConstructor
public class DataPermissionUserDeptController {
    private final DataPermissionService dataPermissionService;

    @GetMapping("/query")
    public ResponseResult<DataPermissionDeptMatcherRespVo> select(@RequestParam("userId") Long userId) {
        return ResponseResult.success(dataPermissionService.selectDeptMatchersByUserId(userId));
    }

    @PostMapping("/update")
    public void updateDataPermission(@RequestBody DataPermissionDeptMatcherUpdateReqVo reqVo) {
        dataPermissionService.updateDeptMatcher(reqVo);
    }
}
