package com.chainxi.system.respvo.oss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssUploadCredentialsRespVo {
    private String objectName;
    private String url;
}
