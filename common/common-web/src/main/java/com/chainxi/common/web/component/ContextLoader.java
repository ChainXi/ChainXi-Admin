package com.chainxi.common.web.component;


import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class ContextLoader implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
        if (ctx == null) {
            ctx = context;
        }
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }

    /**
     * 通过注解得到类型
     *
     * @param clazz
     * @return
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz) {
        return ctx.getBeansWithAnnotation(clazz);
    }


    public static Object getBean(String name) {
        return ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return (T) ctx.getBean(requiredType);
    }
}
