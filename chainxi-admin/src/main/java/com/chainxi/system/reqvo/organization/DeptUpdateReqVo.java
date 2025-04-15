package com.chainxi.system.reqvo.organization;

import com.chainxi.common.web.constant.CommonStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptUpdateReqVo {
    private static final long serialVersionUID = -1L;

    private Long id;

    private String deptName;

    private Long leaderId;


    @Schema(description = "显示顺序不能为空", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

}
