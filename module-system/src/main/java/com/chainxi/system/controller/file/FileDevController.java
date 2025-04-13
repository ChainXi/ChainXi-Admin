package com.chainxi.system.controller.file;

import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import com.chainxi.system.service.oss.OssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.security.PermitAll;
import java.io.File;
import java.io.FileInputStream;

@Profile(SystemEnv.DEV)
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class FileDevController {
    private final OssService ossService;
    @Value("${chainxi.res.path}")
    private String path;


    @GetMapping("/get-oss-upload-sign-url")
    public ResponseResult<OssUploadCredentialsRespVo> getOSSSignUploadUrl() {
        return ResponseResult.success(ossService.getOSSSignUploadUrl());
    }


    @GetMapping(value = "/img/{objectName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @PermitAll
    public byte[] getFile(@PathVariable String objectName) {
        try {
            File file = new File(path, objectName);
            if (!file.exists()) {
                return null;
            }
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @CrossOrigin(allowCredentials = "true", originPatterns = "*")
    @PutMapping("/{objectName}")
    @PermitAll
    public ResponseResult uploadFile(@PathVariable String objectName,
            @RequestParam MultipartFile file) {
        File filePath = new File(path, objectName);
        if (!filePath
                .getParentFile()
                .exists()) {
            log.info("mkdirs: {}", "" + filePath + filePath
                    .getParentFile()
                    .mkdirs());
        }
        try {
            file.transferTo(filePath);
            return ResponseResult.success();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ResponseResult.success();
    }
}
