package com.chainxi.system.service.menu;

import com.chainxi.system.entity.menu.SysResourceDo;
import com.chainxi.system.mapper.menu.SysResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : ChainXi
 * @Date : 2024/6/23 2:51
 * @Desc :
 */
@Service
@RequiredArgsConstructor
public class ClientResourceServiceImpl implements ClientResourceService {
    @Value("${spring.application.name}")
    private String applicationName;
    private final List<RequestMappingHandlerMapping> requestMappingHandlerMapping;
    private final SysResourceMapper sysResourceMapper;

    @Override
    public void sync() {
        List<SysResourceDo> list = requestMappingHandlerMapping
                .stream()
                .flatMap(e -> e
                        .getHandlerMethods()
                        .entrySet()
                        .stream())
                .map(entry -> new SysResourceDo()
                        .setName(entry
                                .getValue()
                                .toString())
                        .setCategoryId(applicationName)
                        .setPatterns(entry
                                .getKey()
                                .getPatternValues()
                                .toString())
                        .setMethods(entry
                                .getKey()
                                .getMethodsCondition()
                                .getMethods()
                                .toArray(new RequestMethod[0])))
                .collect(Collectors.toList());
        sysResourceMapper.insertBatch(list);
    }
}
