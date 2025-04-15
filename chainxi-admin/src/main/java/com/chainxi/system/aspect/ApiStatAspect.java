package com.chainxi.system.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * @Author : ChainXi
 * @Date : 2025/4/19 19:14
 * @Desc :
 */
@Aspect
@Slf4j
//@Component
@RequiredArgsConstructor
public class ApiStatAspect {
    private final static String PREFFIX = "apistat:";
    private final RedisTemplate<Object, Object> redisTemplate;
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    public void apiStat() {}

    /**
     * @param joinPoint
     */
    @Around("apiStat()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AnnotationUtils.findAnnotation(methodSignature.getMethod(),RequestMapping.class).value()
        ;
        String pattern = PREFFIX+Arrays.toString(AnnotationUtils
                .findAnnotation(AopUtils.getTargetClass(joinPoint.getTarget()),RequestMapping.class).value()) + Arrays.toString(methodSignature
                .getMethod()
                .getAnnotation(RequestMapping.class)
                .value());
        redisTemplate.opsForValue().increment(pattern,1);
        return joinPoint.proceed();
    }

    @AfterThrowing(pointcut = "execution(public *  com..controller..*.*(..))", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinpoint, Throwable e) throws Throwable {

    }


}
