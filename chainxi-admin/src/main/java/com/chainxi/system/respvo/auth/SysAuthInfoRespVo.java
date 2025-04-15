package com.chainxi.system.respvo.auth;

import com.chainxi.system.entity.menu.SysMenuDo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Schema(description = "管理后台 - 登录用户的权限信息 Response VO，额外包括用户信息和角色列表")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAuthInfoRespVo {
    String nickName;
    String avatar;
    @Schema(description = "菜单树", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<SysMenuDo> menus;

}
