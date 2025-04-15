package com.chainxi.system.respvo.menu;

import com.chainxi.system.enums.auth.ResourceAccessType;
import com.chainxi.system.enums.auth.ResourceMappingType;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SysResourceRespVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private Long id;
    private String name;
    private String patterns;
    private RequestMethod[] methods;
    private String description;
    private String categoryId;

    private ResourceMappingType mappingType;

    private ResourceAccessType accessType;
}