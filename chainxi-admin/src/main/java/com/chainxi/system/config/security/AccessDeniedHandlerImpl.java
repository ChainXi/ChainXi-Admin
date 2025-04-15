package com.chainxi.system.config.security;

import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.common.web.utils.JsonUtils;
import com.chainxi.common.web.utils.RespUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = ResponseResult.of(HttpStatus.FORBIDDEN.value(), "权限不足");
        String json = JsonUtils.toJSONString(result);
        RespUtils.renderString(response, json);
    }
}


