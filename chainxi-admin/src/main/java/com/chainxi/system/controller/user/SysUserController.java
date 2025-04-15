package com.chainxi.system.controller.user;

import cn.hutool.core.collection.CollUtil;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.common.web.utils.ExcelUtils;
import com.chainxi.system.convert.user.SysUserConvert;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.enums.common.SexEnum;
import com.chainxi.system.reqvo.user.user.*;
import com.chainxi.system.respvo.user.user.*;
import com.chainxi.system.service.user.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {
    private final SysUserService sysUserService;

    @GetMapping("/query")
    @Operation(summary = "获得用户详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult<UserRespVo> getUserDetail(@RequestParam("id") Long id) {
        return ResponseResult.success(SysUserConvert.INSTANCE.convert(sysUserService.getUser(id)));
    }

    @GetMapping("/query-page")
    public ResponseResult<PageResult<UserRespVo>> userPage(@Valid UserPageReqVo reqVo) {
        PageResult<SysUserDo> pageResult = sysUserService.userPage(reqVo);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return ResponseResult.success(PageResult.empty());
        }
        return ResponseResult.success(new PageResult<>(pageResult
                .getList()
                .stream()
                .map(SysUserConvert.INSTANCE::convert)
                .collect(Collectors.toList()), pageResult.getTotal()));
    }

    @GetMapping("/query-index-map-page")
    @Operation(summary = "获取用户精简信息列表", description = "只包含被开启的用户，主要用于前端的下拉选项")
    public ResponseResult<PageResult<UserIndexMapRespVo>> getIndexMapUserPage(@Valid UserIndexMapPageReqVo reqVo) {
        PageResult<SysUserDo> pageResult = sysUserService.userIndexMapPage(reqVo);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return ResponseResult.success(PageResult.empty());
        }
        return ResponseResult.success(new PageResult<>(pageResult
                .getList()
                .stream()
                .map(SysUserConvert.INSTANCE::convertDo2IndexMapVo)
                .collect(Collectors.toList()), pageResult.getTotal()));
    }

    @PostMapping("/create")
    @Operation(summary = "新增用户")
    public ResponseResult<Long> createUser(@Valid @RequestBody UserCreateReqVo reqVo) {
        Long id = sysUserService.createUser(reqVo);
        return ResponseResult.success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户")
    public ResponseResult<Boolean> updateUser(@Valid @RequestBody UserUpdateReqVo reqVo) {
        sysUserService.updateUser(reqVo);
        return ResponseResult.success(true);
    }

    @PutMapping("/update-password")
    @Operation(summary = "重置用户密码")
    public ResponseResult<Boolean> updateUserPassword(@Valid @RequestBody UserUpdatePasswordReqVo reqVo) {
        sysUserService.updateUserPassword(reqVo.getId(), reqVo.getPassword());
        return ResponseResult.success(true);
    }

    @PutMapping("/update-status")
    @Operation(summary = "修改用户状态")
    public ResponseResult<Boolean> updateUserStatus(@Valid @RequestBody UserUpdateStatusReqVo reqVo) {
        sysUserService.updateUserStatus(reqVo.getId(), reqVo.getStatus());
        return ResponseResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        sysUserService.deleteUser(id);
        return ResponseResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出用户")
    public void exportUserList(@Validated UserExportReqVo reqVo, HttpServletResponse response) throws IOException {
        // 获得用户列表
        List<SysUserDo> users = sysUserService.getUserList(reqVo);
        // 拼接数据
        List<UserExcelVo> excelUsers = new ArrayList<>(users.size());
        users.forEach(user -> {
            UserExcelVo excelVO = SysUserConvert.INSTANCE.convert02(user);
            excelUsers.add(excelVO);
        });

        // 输出
        ExcelUtils.write(response, "用户数据.xls", "用户列表", UserExcelVo.class, excelUsers);
    }

    @GetMapping("/download-import-template")
    @Operation(summary = "获得导入用户模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<UserImportExcelVo> list = Arrays.asList(UserImportExcelVo
                .builder()
                .userName("admin")
                .email("admin@_._")
                .phoneNumber("15601691300")
                .nickName("admin")
                .status(CommonStatusEnum.ENABLE)
                .sex(SexEnum.MALE.getSex())
                .build(), UserImportExcelVo
                .builder()
                .userName("admin")
                .email("admin@_.com")
                .phoneNumber("15601701300")
                .nickName("源码")
                .status(CommonStatusEnum.DISABLE)
                .sex(SexEnum.FEMALE.getSex())
                .build());

        // 输出
        ExcelUtils.write(response, "用户导入模板.xls", "用户列表", UserImportExcelVo.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入用户")
    @Parameters({@Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")})
    public ResponseResult<UserImportRespVo> importExcel(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<UserImportExcelVo> list = ExcelUtils.read(file, UserImportExcelVo.class);
        return ResponseResult.success(sysUserService.importUserList(list, updateSupport));
    }

}
