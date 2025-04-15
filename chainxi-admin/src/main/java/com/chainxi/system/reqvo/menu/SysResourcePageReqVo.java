package com.chainxi.system.reqvo.menu;

import com.chainxi.common.web.pojo.PageParam;
import com.chainxi.system.enums.auth.ResourceAccessType;
import com.chainxi.system.enums.auth.ResourceMappingType;
import lombok.Data;

import java.io.Serial;

@Data
public class SysResourcePageReqVo extends PageParam {
    @Serial
    private static final long serialVersionUID = -1L;
    private String name;
    private String patterns;
    private String categoryId;

    private ResourceMappingType mappingType;

    private ResourceAccessType accessType;
}
