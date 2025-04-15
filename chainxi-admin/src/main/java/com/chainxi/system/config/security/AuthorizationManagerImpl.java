package com.chainxi.system.config.security;

import com.chainxi.system.bo.RequestMappingBo;
import com.chainxi.system.service.menu.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.util.ServletRequestPathUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorizationManagerImpl implements AuthorizationManager<RequestAuthorizationContext> {
    private final ResourceService resourceService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> supplier,
            RequestAuthorizationContext authorizationContext) {
        RequestMappingBo requestMappingBo = resourceService.getResourceRolesMap();
        HttpServletRequest request = authorizationContext.getRequest();
        ServletRequestPathUtils.parseAndCache(request);
        if (requestMappingBo
                .getRegistryAnonymous()
                .stream()
                .anyMatch(mapping -> mapping.getMatchingCondition(request) != null)) {
            return new AuthorizationDecision(true);
        }
        Authentication authentication = supplier.get();
        if (!authentication.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }
        if (requestMappingBo
                .getRegistryAuthenticated()
                .stream()
                .anyMatch(mapping -> mapping.getMatchingCondition(request) != null)) {
            return new AuthorizationDecision(true);
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }
        Optional<Map.Entry<RequestMappingInfo, List<Long>>> entryOptional = requestMappingBo
                .getRegistryAuthority()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getMatchingCondition(request) != null)
                .min((o1, o2) -> o1.getKey().compareTo(o2.getKey(), request));
        if (entryOptional.isEmpty()) {
            return new AuthorizationDecision(true);
        }
        List<String> requestAuthorities = entryOptional
                .map(requestMappingInfoListEntry -> requestMappingInfoListEntry
                        .getValue()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        if (requestAuthorities.isEmpty()) {
            return new AuthorizationDecision(false);
        }

        return new AuthorizationDecision(authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(requestAuthorities::contains));
    }
}
