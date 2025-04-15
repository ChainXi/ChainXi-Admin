package com.chainxi.system.respvo.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门表 sys_dept
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDeptMapRespVo {
    private static final long serialVersionUID = -1L;
    /**
     * 部门ID 8*8 level*num
     */
    private Long id;

    /**
     * 部门名称
     */
    private String deptName;
}
