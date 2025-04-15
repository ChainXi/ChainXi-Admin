package com.chainxi.system.config.datapermission;

import com.chainxi.system.mapper.datapermission.DataPermissionDeptMatcherMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : ChainXi
 * @Date : 2024/10/27 14:59
 * @Desc :
 */

@Configuration(proxyBeanMethods = false)
public class DataPermissionAutoConfiguration {

    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(DataPermissionDeptMatcherMapper deptMatcherMapper){
        return new DeptDataPermissionRule(deptMatcherMapper);
    }



}
