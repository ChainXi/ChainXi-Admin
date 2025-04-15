package com.chainxi.system.entity.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chainxi.common.web.handler.EnumArrayOrdinalTypeHandler;
import com.chainxi.common.web.pojo.BaseDo;
import com.chainxi.system.enums.auth.ResourceAccessType;
import com.chainxi.system.enums.auth.ResourceMappingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serial;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_resource", autoResultMap = true)
public class SysResourceDo extends BaseDo {
    @Serial
    private static final long serialVersionUID = -1L;
    @TableId
    private Long id;
    private String name;
    private String patterns;
    @TableField(typeHandler = EnumArrayOrdinalTypeHandler.class, javaType = true)
    private RequestMethod[] methods;
    private String description;
    private String categoryId;
    private ResourceMappingType mappingType;
    private ResourceAccessType accessType;
}