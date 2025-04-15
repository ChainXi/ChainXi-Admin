package com.chainxi.system.controller.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.convert.auth.SysAuthConvert;
import com.chainxi.system.entity.token.RefreshTokenDo;
import com.chainxi.system.reqvo.token.TokenPageReqVO;
import com.chainxi.system.respvo.auth.TokenRespVo;
import com.chainxi.system.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@Tag(name = "管理后台 - OAuth2.0 令牌")
@RestController
@RequestMapping("/system/oauth2-token")
@RequiredArgsConstructor
public class TokenController {

    private final AuthService authService;

    @GetMapping("/page")
    @Operation(summary = "获得访问令牌分页", description = "只返回有效期内的")
    public ResponseResult<PageResult<TokenRespVo>> getAccessTokenPage(@Valid TokenPageReqVO reqVo) {
        PageResult<RefreshTokenDo> pageResult = authService.getAccessTokenPage(reqVo);
        return ResponseResult.success(SysAuthConvert.INSTANCE.convert2Page(pageResult));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    public ResponseResult<Boolean> deleteAccessToken(@RequestParam("rt") String refreshToken) {
        authService.removeTokenByRt(refreshToken);
        return ResponseResult.success(true);
    }

}
