package com.chainxi.common.web.utils;

import com.chainxi.common.web.component.ContextLoader;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 动态注册注销Spring Bean
 *
 * @author guzt
 */
public class DynamicRegisterUtil {

    /**
     * 动态注册Bean
     *
     * @param beanName    bean名称
     * @param targetClass bean对应的类
     */
    public static void registerBeanDefinition(String beanName, Class<?> targetClass) {
        ApplicationContext applicationContext = ContextLoader.getCtx();
        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory =
                (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        //创建bean信息.
        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(targetClass);
        //动态注册bean.
        defaultListableBeanFactory.registerBeanDefinition(beanName,
                beanDefinitionBuilder.getBeanDefinition());
    }

    /**
     * 动态卸载Bean
     *
     * @param beanName bean名称
     */
    public static void unRegisterBeanDefinition(String beanName) {
        ApplicationContext applicationContext = ContextLoader.getCtx();
        if (!applicationContext.containsBean(beanName)) {
            return;
        }
        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory =
                (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }

    /**
     * 动态注册Controller
     *
     * @param controllerBeanName controller的beanName
     * @throws Exception 反射异常
     */
    public static void registerController(String controllerBeanName)
            throws Exception {
        final RequestMappingHandlerMapping requestMappingHandlerMapping =
                ContextLoader.getCtx().getBean(RequestMappingHandlerMapping.class);
        if (requestMappingHandlerMapping != null) {
            Object controller = ContextLoader.getCtx().getBean(controllerBeanName);
            if (controller == null) {
                return;
            }
            //注册Controller
            Method method = requestMappingHandlerMapping.getClass().getSuperclass().getSuperclass().
                    getDeclaredMethod("detectHandlerMethods", Object.class);
            //将private改为可使用
            method.setAccessible(true);
            method.invoke(requestMappingHandlerMapping, controllerBeanName);
        }
    }

    /**
     * 动态去掉Controller的Mapping
     *
     * @param controllerBeanName controller的beanName
     */
    public static void unregisterController(String controllerBeanName) {
        final RequestMappingHandlerMapping requestMappingHandlerMapping =
                ContextLoader.getCtx().getBean(RequestMappingHandlerMapping.class);
        if (requestMappingHandlerMapping != null) {
            Object controller = ContextLoader.getCtx().getBean(controllerBeanName);
            if (controller == null) {
                return;
            }
            final Class<?> targetClass = controller.getClass();
            ReflectionUtils.doWithMethods(targetClass, method -> {
                Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
                try {
                    Method createMappingMethod = RequestMappingHandlerMapping.class.
                            getDeclaredMethod("getMappingForMethod", Method.class, Class.class);
                    createMappingMethod.setAccessible(true);
                    RequestMappingInfo requestMappingInfo = (RequestMappingInfo)
                            createMappingMethod.invoke(requestMappingHandlerMapping,
                                    specificMethod,
                                    targetClass);
                    if (requestMappingInfo != null) {
                        requestMappingHandlerMapping.unregisterMapping(requestMappingInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, ReflectionUtils.USER_DECLARED_METHODS);
        }
    }
}