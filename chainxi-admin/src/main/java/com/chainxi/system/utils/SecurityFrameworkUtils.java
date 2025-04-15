package com.chainxi.system.utils;


import com.chainxi.system.bo.AuthUserBo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.util.Optional;

/**
 * 安全服务工具类
 */
public class SecurityFrameworkUtils {
    private SecurityFrameworkUtils() {}

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    @Nullable
    public static AuthUserBo getLoginUser() {
        return (AuthUserBo) Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(context->context.getAuthentication().getPrincipal())
                .filter(principal -> principal instanceof AuthUserBo)
                .orElse(null);
    }

    /**
     * 设置当前用户
     *
     * @param userDetails 登录用户
     * @param request     请求
     */
    public static void setLoginUser(UserDetails userDetails, HttpServletRequest request) {
        // 创建 Authentication，并设置到上下文
        Authentication authentication = buildAuthentication(userDetails, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 额外设置到 request 中，用于 ApiAccessLogFilter 可以获取到用户编号；
        // 原因是，Spring Security 的 Filter 在 ApiAccessLogFilter 后面，在它记录访问日志时，线上上下文已经没有用户编号等信息
//        WebFrameworkUtils.setLoginUserId(request, userBo.getId());
//        WebFrameworkUtils.setLoginUserType(request, userBo.getUserType());
    }

    private static Authentication buildAuthentication(UserDetails userDetails,
            HttpServletRequest request) {
        // 创建 UsernamePasswordAuthenticationToken 对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }

}
