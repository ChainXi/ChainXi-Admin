package com.chainxi.system.service.oss;

import cn.hutool.core.lang.UUID;
import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Profile(SystemEnv.DEV)
@Service
@RequiredArgsConstructor
@Slf4j
public class OssServiceDevImpl implements OssService {
    private final Environment environment;

    @Override
    public OssUploadCredentialsRespVo getOSSSignUploadUrl() {
        String objectName = UUID.randomUUID().toString(true);
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String port = environment.getProperty("server.port");
            return new OssUploadCredentialsRespVo(objectName,
                    "http://" + ip + ":" + port + "/file/" + objectName);
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
            return new OssUploadCredentialsRespVo(objectName, "#");
        }
    }
}
