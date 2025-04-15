package com.chainxi.system.controller.auth;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.config.sso.SsoProperties;
import com.chainxi.system.memorystore.oauth.OAuthClientStore;
import com.chainxi.system.reqvo.auth.OAuthCertificateReqVo;
import com.chainxi.system.respvo.auth.TokenRARespVo;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import com.chainxi.system.utils.UrlUtils;
import com.ejlchina.okhttps.OkHttps;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


//@RestController
@RequestMapping("/sso/client")
@Slf4j
@RequiredArgsConstructor
public class SysClientController {
    private final SsoProperties ssoProperties;
    private final ObjectMapper objectMapper;
    private final OAuthClientStore oAuthClientStore;
    private final TypeReference<ResponseResult<TokenRARespVo>> TYPE_REF = new TypeReference<>() {};

    @GetMapping("/login")
    @PermitAll
    public ResponseResult applyCode(@RequestParam(name = "code", required = false) Long code,
            @RequestParam(name = "back", required = false) String back,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (code != null) {
            String resStr = OkHttps
                    .sync(ssoProperties.serverUrl + ssoProperties.checkTicketUrl)
                    .setBodyPara(new OAuthCertificateReqVo()
                            .setCode(code)
                            .setClientId(ssoProperties.clientId)
                            .setClientSecret(ssoProperties.clientSecret))
                    .post()
                    .getBody()
                    .toString();

            ResponseResult<TokenRARespVo> responseResult =
                    objectMapper.readValue(resStr, TYPE_REF);
            Long clientToken = DefaultIdentifierGenerator
                    .getInstance()
                    .nextId(null);
            oAuthClientStore.putUser(clientToken, responseResult
                    .getData()
                    .getUserId());
            return ResponseResult.success(clientToken);
        } else if (back != null) {
            AuthUserBo loginUser = SecurityFrameworkUtils.getLoginUser();
            if (loginUser != null) {
                response.sendRedirect(back);
            }
            String redirect = request
                    .getRequestURL()
                    .toString();
            redirect =
                    UrlUtils.joinParam(redirect, "back", URLEncoder.encode(back,
                            StandardCharsets.UTF_8));
            List<Pair<String, Object>> params =
                    List.of(new Pair<>("redirect", redirect), new Pair<>("client_id",
                            ssoProperties.clientId));
            String url = ssoProperties.serverUrl + ssoProperties.authUrl;
            url = UrlUtils.joinParam(url, params);
            response.sendRedirect(url);
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @GetMapping("/logout")
    public ResponseResult logout() {
        return null;
    }

    @GetMapping("/logout-call")
    public ResponseResult logoutCall() {
        return null;
    }

    @GetMapping("/refresh-token")
    public ResponseResult refreshToken() {
        return null;
    }

}
