package com.chainxi.system.controller.file;

import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import com.chainxi.system.service.oss.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile(SystemEnv.PRD)
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final OssService ossService;

    @GetMapping("/get-oss-upload-sign-url")
    public ResponseResult<OssUploadCredentialsRespVo> getOSSSignUploadUrl() {
        return ResponseResult.success(ossService.getOSSSignUploadUrl());
    }
}
