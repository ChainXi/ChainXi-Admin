package com.chainxi.common.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.chainxi.common.web.handler.DefaultDBFieldHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@AutoConfiguration
@EnableTransactionManagement
@Slf4j
public class MybatisPlusAutoConfiguration {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(List<InnerInterceptor> innerInterceptorList) {
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.setInterceptors(innerInterceptorList);
        return plusInterceptor;
    }


    @Bean
    @ConditionalOnMissingBean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }

    @Bean
    public InnerInterceptor getPaginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOptimizeJoin(true);
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setOverflow(true);
        return paginationInnerInterceptor;
    }

    @Bean
    public InnerInterceptor getOptimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }


    @Bean
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return properties -> {
            MybatisPlusProperties.CoreConfiguration configuration =
                    new MybatisPlusProperties.CoreConfiguration();
            configuration.setDefaultEnumTypeHandler(EnumOrdinalTypeHandler.class);
            properties.setConfiguration(configuration);
        };
    }
}