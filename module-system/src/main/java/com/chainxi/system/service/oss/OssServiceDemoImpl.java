package com.chainxi.system.service.oss;

import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(SystemEnv.DEMO)
@Service
@RequiredArgsConstructor
public class OssServiceDemoImpl implements OssService {
    @Override
    public OssUploadCredentialsRespVo getOSSSignUploadUrl() {
        throw new BizException(ErrorCodeConstants.PROFILE_DEMO_UPDATE_DENIED);
    }
}
