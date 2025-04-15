package com.chainxi.common.web.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;


@Aspect
@Slf4j
@RequiredArgsConstructor
public class WebLogAspect {
    private final ObjectMapper objectMapper;
    //    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private final String LINEBREAK = System.getProperty("line.separator");

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public *  com..controller..*.*(..))")
    public void webLog() {}

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] returnNameArr = methodSignature.getReturnType().getName().split("\\.");
        Class clazz = signature.getDeclaringType();
        //请求url
        String url = request.getRequestURL().toString();
        //请求参数
        String param = getParam(proceedingJoinPoint);
        //方法名
        String method = signature.getName();
        //行数
//        int num = lineNum("public" + returnNameArr[returnNameArr.length - 1] + method + "(",
//        clazz);
        int num = 0;
        // 记录info日志
        StringBuilder logInfo = new StringBuilder();
        logInfo.append(":\n");
        logInfo.append("+========================================= Start " +
                "==========================================\n");
        // 记录请求源ip
        logInfo.append("| Request IP     : ").append(request.getRemoteAddr()).append("\n");
        // 记录请求url
        logInfo.append("| URL            : ").append(url).append("\n");
        // 记录请求的方式
        logInfo.append("| HTTP Method    : ").append(request.getMethod()).append("\n");
        // 记录类名和方法名
        logInfo
                .append("| Class Method   : ")
                .append(signature.getDeclaringTypeName())
                .append(".")
                .append(method)
                .append("(")
                .append(clazz.getSimpleName())
                .append(".java")
                .append(")")
                .append(LINEBREAK);
        // 记录请求参数
        logInfo.append("| Request Args   : ").append(param).append("\n");
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logInfo
                .append("| Response Args  : ")
                .append(objectMapper.writeValueAsString(result))
                .append("\n");
        // 执行耗时
        logInfo
                .append("| Time-Consuming : ")
                .append(System.currentTimeMillis() - startTime)
                .append("ms")
                .append("\n");
        logInfo.append("+========================================== End " +
                "===========================================\n");
        log.info(logInfo.toString());
        return result;
    }

    @AfterThrowing(pointcut = "execution(public *  com..controller..*.*(..))", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinpoint, Throwable e) throws Throwable {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录info日志
        StringBuilder logInfo = new StringBuilder();
        logInfo.append(":\n");
        logInfo.append("+========================================= Start " +
                "==========================================\n");
        // 记录请求源ip
        logInfo.append("| IP             : ").append(request.getRemoteAddr()).append("\n");
        // 记录请求uri
        logInfo
                .append("| URL            : ")
                .append(request.getRequestURL().toString())
                .append("\n");
        // 记录请求的方式
        logInfo.append("| HTTP Method    : ").append(request.getMethod()).append("\n");
        // 记录类名和方法名
        logInfo
                .append("| Class Method   : ")
                .append(joinpoint.getSignature().getDeclaringTypeName())
                .append(".")
                .append(joinpoint.getSignature().getName())
                .append("\n");
        // 记录请求参数
        logInfo.append("| Request Args   : ").append(getParam(joinpoint)).append("\n");
        logInfo.append("+===================================== Exception END " +
                "======================================\n");
        log.info(logInfo.toString());
        throw e;
    }


    /**
     * @Description: 返回方法在该类的行数
     * @Param: codeFragment 方法名 fileName 类的文件名
     * @return: 所在行数
     */
    private static int lineNum(String codeFragment, Class clazz) throws URISyntaxException {
        int lineNum = 1;
        Path path = Paths.get(clazz
                .getClassLoader()
                .getResource(clazz.getName().replaceAll("\\.", "/") + ".class")
                .toURI());
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                lineNum = i + 1;
                if (deleteWhitespace(line).toLowerCase().startsWith(codeFragment.toLowerCase())) {
                    break;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return lineNum;
    }

    /**
     * 获取参数名和参数值
     *
     * @param joinPoint
     * @return 返回JSON结构字符串
     */
    @SneakyThrows
    public String getParam(JoinPoint joinPoint) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        Object[] values = joinPoint.getArgs();
        String[] names = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < names.length; i++) {
            if (values[i] instanceof MultipartFile) {
                MultipartFile value = (MultipartFile) values[i];
                map.put(names[i], value.getName() + ":" + value.getSize());
            } else if (values[i] instanceof InputStreamSource) {
                map.put(names[i], values[i].toString());
            } else if (!(values[i] instanceof ServletResponse)) {
                map.put(names[i], values[i]);
            }
        }
        return objectMapper.writeValueAsString(map);
    }

}
