package com.chainxi.system.respvo.organization;

import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.system.respvo.user.user.UserIndexMapRespVo;
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
public class SysDeptDetailRespVo {
    private static final long serialVersionUID = -1L;
    /**
     * 部门ID 8*8 level*num
     */
    private Long id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 负责人
     */
    private UserIndexMapRespVo leader;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 部门状态:0正常,1停用
     */
    private CommonStatusEnum status;
}
