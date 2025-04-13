package com.chainxi.system.controller.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.entity.token.OAuth2ClientDo;
import com.chainxi.system.reqvo.auth.OAuth2ClientInfoPageReqVo;
import com.chainxi.system.reqvo.auth.Oauth2ClientInfoUpdateReqVo;
import com.chainxi.system.service.auth.OAuth2Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "管理后台 - 缓存管理")
@RestController
@RequestMapping("/sys/oauth2-client")
@RequiredArgsConstructor
public class SysOAuth2InfoController {
    private final OAuth2Service oauth2Service;

    @GetMapping("/query")
    public ResponseResult<OAuth2ClientDo> select(@RequestParam("clientId") Long clientId) {
        return ResponseResult.success(oauth2Service.selectOne(clientId));
    }

    @GetMapping("/query-page")
    public ResponseResult<PageResult<OAuth2ClientDo>> selectPage(OAuth2ClientInfoPageReqVo reqVo) {
        return ResponseResult.success(oauth2Service.selectPage(reqVo));
    }

    @PutMapping("/create")
    public ResponseResult createClientInfo(@RequestBody Oauth2ClientInfoUpdateReqVo reqVo) {
        oauth2Service.createClient(reqVo);
        return ResponseResult.success();
    }

    @PostMapping("/update")
    public ResponseResult updateClientInfo(@RequestBody Oauth2ClientInfoUpdateReqVo reqVo) {
        oauth2Service.updateClient(reqVo);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteClientInfoByClientId(@RequestParam("client_id") Long clientId) {
        oauth2Service.deleteClient(clientId);
        return ResponseResult.success();
    }
}
