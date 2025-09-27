package com.chainxi.system.config.security;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.memorystore.token.TokenStore;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final TokenStore tokenStore;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException {
        Map<String, Cookie> cookies = getCookies(request);
        if(!cookies.containsKey("did")){
            Cookie did = new Cookie("did", String.valueOf(DefaultIdentifierGenerator
                    .getInstance()
                    .nextId(null)));
            did.setPath("/");
            response.addCookie(did);
        }
        String userId = Optional.ofNullable(cookies.get("uid")).map(Cookie::getValue).orElse(null);
        if (!StringUtils.hasText(userId)) {
            logger.debug("Anonymous access:null uid");
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("access_token");
        if (!StringUtils.hasText(token)) {
            logger.debug("Anonymous access:null access_token");
            filterChain.doFilter(request, response);
            return;
        }
        AuthUserBo authUserBo = tokenStore.getAuthUser(token);
        if (authUserBo == null || !authUserBo.getUserId().toString().equals(userId)) {
            logger.debug("Anonymous access:invalid authUserBo");
            filterChain.doFilter(request, response);
            return;
        }
        logger.debug(String.format("user access userId %s", authUserBo.getUserId()));
        //存入SecurityContextHolder
        SecurityFrameworkUtils.setLoginUser(authUserBo, request);
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(authUserBo,
                        null,
                        authUserBo.getAuthorities()));
        filterChain.doFilter(request, response);
    }
    @Nonnull
    private Map<String, Cookie> getCookies(HttpServletRequest request) {
        Cookie[] cookiesArr = request.getCookies();
        if (cookiesArr == null) {
            return Collections.emptyMap();
        }
        return Arrays
                .stream(cookiesArr)
                .collect(Collectors.toMap(Cookie::getName, Function.identity(), (cookie, cookie2) -> cookie));
    }

}

