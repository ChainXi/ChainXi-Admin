package com.chainxi.system.reqvo.menu;

import com.chainxi.system.enums.auth.ResourceAccessType;
import com.chainxi.system.enums.auth.ResourceMappingType;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
public class SysResourceUpdateReqVo {
    private Long id;
    private String name;
    private String patterns;
    private RequestMethod[] methods;
    private String description;
    private String categoryId;

    private ResourceMappingType mappingType;

    private ResourceAccessType accessType;
}