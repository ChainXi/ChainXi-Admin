package com.chainxi.system.bo;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author : ChainXi
 * @Date : 2024/6/26 23:57
 * @Desc :
 */
@Data
public class RequestMappingBo {
    private Map<RequestMappingInfo, List<Long>> registryAuthority;
    private Map<String, RequestMappingInfo> pathLookupAuthority;
    private Set<RequestMappingInfo> registryAuthenticated;
    private Map<String, RequestMappingInfo> pathLookupAuthenticated;
    private Set<RequestMappingInfo> registryAnonymous;
    private Map<String, RequestMappingInfo> pathLookupAnonymous;
}

