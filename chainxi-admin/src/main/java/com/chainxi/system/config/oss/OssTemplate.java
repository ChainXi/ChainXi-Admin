package com.chainxi.system.config.oss;

import cn.hutool.core.lang.UUID;
import com.aliyun.oss.*;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.chainxi.system.respvo.oss.OssUploadCredentialsRespVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
public class OssTemplate {

    private final OssProperties properties;

    public OssUploadCredentialsRespVo getOSSSignUploadUrl() {
        String endpoint = properties.getEndpoint();
        String bucketName = properties.getBucketName();
        String objectName = UUID.randomUUID().toString(true);
        OSS ossClient = new OSSClientBuilder().build(endpoint,
                properties.getAccessKey(),
                properties.getSecret());
        try {
            Date expiration = new Date(new Date().getTime() + 600 * 1000L);
            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.PUT);
            request.setExpiration(expiration);
            request.setHeaders(new HashMap<>());
            request.setUserMetadata(new HashMap<>());
            URL url = ossClient.generatePresignedUrl(request);
            log.info("signed url for putObject: " + url);
            if (url != null) {
                return new OssUploadCredentialsRespVo(objectName, url.toString());
            }
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, " +
                    "but was rejected with an error response for some reason.");
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:" + oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with OSS, " +
                    "such as not being able to access the network.");
            log.error("Error Message:" + ce.getMessage());
        }
        return null;
    }
}

