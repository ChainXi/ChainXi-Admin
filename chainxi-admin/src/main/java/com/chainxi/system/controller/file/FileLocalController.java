package com.chainxi.system.controller.file;

import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.mapper.file.DemoFile;
import com.chainxi.system.mapper.file.FileLocalMapper;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import com.chainxi.system.service.oss.OssService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Profile({SystemEnv.DEV, SystemEnv.DEMO})
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class FileLocalController {
    private final OssService ossService;
    private final FileLocalMapper fileLocalMapper;

    @Profile(SystemEnv.DEV)
    @GetMapping("/get-oss-upload-sign-url")
    public ResponseResult<OssUploadCredentialsRespVo> getOSSSignUploadUrl() {
        return ResponseResult.success(ossService.getOSSSignUploadUrl());
    }


    @GetMapping(value = "/img/{objectName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @PermitAll
    public byte[] getFile(@PathVariable String objectName) {
        DemoFile demoFile = fileLocalMapper.selectById(objectName);
        return demoFile != null ? demoFile.getData() : null;
    }

    @Profile(SystemEnv.DEV)
    @CrossOrigin(allowCredentials = "true", originPatterns = "*")
    @PutMapping("/{objectName}")
    @PermitAll
    public ResponseResult uploadFile(@PathVariable String objectName,
            @RequestParam MultipartFile file) throws IOException {
        fileLocalMapper.insert(new DemoFile(objectName, file.getBytes()));
        return ResponseResult.success();
    }
}
