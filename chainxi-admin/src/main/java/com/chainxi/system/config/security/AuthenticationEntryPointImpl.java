package com.chainxi.system.config.security;

import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.common.web.utils.JsonUtils;
import com.chainxi.common.web.utils.RespUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) {
        ResponseResult result =
                ResponseResult.of(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        String json = JsonUtils.toJSONString(result);
        RespUtils.renderString(response, json);
    }
}


