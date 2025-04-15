package com.chainxi.system.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleBo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名
     */
    private String name;

}
