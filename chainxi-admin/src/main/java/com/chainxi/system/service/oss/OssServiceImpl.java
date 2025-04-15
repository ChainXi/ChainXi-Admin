package com.chainxi.system.service.oss;

import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.system.config.oss.OssTemplate;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(SystemEnv.PRD)
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {
    private final OssTemplate ossTemplate;

    @Override
    public OssUploadCredentialsRespVo getOSSSignUploadUrl() {
        return ossTemplate.getOSSSignUploadUrl();
    }
}
