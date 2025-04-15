package com.chainxi.system.config.oss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "chainxi.oss")
public class OssProperties {
    private String accessKey;
    private String secret;
    private String bucketName;
    private String endpoint;
}
