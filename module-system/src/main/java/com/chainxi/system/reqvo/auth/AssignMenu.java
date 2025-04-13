package com.chainxi.system.reqvo.auth;

import com.chainxi.system.enums.auth.ResourceMappingType;
import lombok.Data;

@Data
public class AssignMenu {
    private Long menuId;
    private ResourceMappingType mappingType;
}