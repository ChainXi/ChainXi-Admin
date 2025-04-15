package com.chainxi.om.aspect;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.chainxi.common.web.constant.HttpCodeEnum;
import com.chainxi.common.web.pojo.ResponseResult;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

//TBD otel自带实现
//@Component
@Slf4j
@Aspect
public class PrometheusAspect {
 
    /**
     * 定义注册器
     */
    @Autowired
    MeterRegistry registry;
 
    private Counter counter_all_total;
    private AtomicInteger pc_online_count;
    private ConcurrentHashMap<String,Counter> counterMap = new ConcurrentHashMap();
 
   ThreadLocal<Long> startTime = new ThreadLocal<>();
 
    /**
     * 监控controller层的接口
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    private void pointCut(){}
 
    /**
     * 启动时初始化指标，所有接口的请求次数统计
     */
    @PostConstruct
    public void init(){
        counter_all_total = registry.counter("pc_aop_all_requests_count", "aop_all_method", "count");
        pc_online_count = registry.gauge("pc_all_online_count", new AtomicInteger(0));
    }
 
    /**
     * 有些业务场景也可以在这里监控接口
     * @param joinPoint
     * @throws Throwable
     */
  //  @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint )throws Throwable {
        Counter counter_total = null;
 
        String classMethod = getClassMethodName(joinPoint);
        if(counterMap.containsKey(classMethod)){
            counter_total = counterMap.get(classMethod);
        }else {
            counter_total = registry.counter("pc_aop_requests_count", "aop_method", classMethod);
            counterMap.put(classMethod,counter_total);
        }
        startTime.set(System.currentTimeMillis());
        counter_total.increment();
        counter_all_total.increment();
    }
 
    /**
     * 获取请求的接口名
     * @param joinPoint
     * @return
     */
    private String getClassMethodName(JoinPoint joinPoint) {
        //类方法
        log.info("classMethod={}", joinPoint.getSignature().getDeclaringTypeName());
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String className = declaringTypeName.substring(declaringTypeName.lastIndexOf(".")+1);
        log.info("className={}", className);
        log.info("Method={}", joinPoint.getSignature().getName());
        return className +"."+ joinPoint.getSignature().getName();
    }
 
    /**
     * 获取请求的参数
     * @param joinPoint
     * @return
     */
    private String getMethodArgs(ProceedingJoinPoint joinPoint){
        // 构造参数组集合
        List<Object> argList = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
            // request/response无法使用toJSON
            if (arg instanceof HttpServletRequest) {
                argList.add("request");
            } else if (arg instanceof HttpServletResponse) {
                argList.add("response");
            } else if (arg instanceof MultipartFile){
                argList.add("file");
            } else {
                argList.add(new JSONObject(arg).toString());
            }
        }
        return new JSONArray(argList).toString();
    }
 
    //@Before("pointcutImage()")
   /* public void doBefore(JoinPoint joinPoint)throws Throwable {
        startTime.set(System.currentTimeMillis());
        counter_total.increment();
    }*/
 
    /**
     * 每个接口的耗时数据采集
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * 改服务请求的总次数
         */
        counter_all_total.increment();
        String classMethod = getClassMethodName(joinPoint);
        Timer timer = Metrics.timer("pc_reponse_usedtime", "method_name", classMethod);
        /**
         * 该服务中每个接口请求过程没有抛异常的次数、最大响应时间、总的请求时间
         */
        Object result = timer.recordCallable(() -> {
            try {
                Object proceed = joinPoint.proceed();
                /**
                 * 该服务中每个接口请求后返回错误码的次数
                 */
                if(proceed instanceof ResponseResult) {
                    ResponseResult r = (ResponseResult) proceed;
                    if (r.getCode() != HttpCodeEnum.SUCCESS.getCode()) {
                        Counter reponse_error_total = registry.counter("pc_reponse_error_count", "aop_method", classMethod);
                        reponse_error_total.increment();
                        String methodArgs = getMethodArgs(joinPoint);
                        log.info("接口返回错误码[{}],接口返回错误信息[{}],错误接口名是[{}],所传参数[{}]",r.getCode(),r.getMsg(),classMethod,methodArgs);
                    }
                }
                return proceed;
            } catch (Throwable e) {
                /**
                 * 该服务中每个接口请求过程中抛异常的次数
                 */
                Counter reponse_throw_total = registry.counter("pc_reponse_throw_count", "aop_method", classMethod);
                reponse_throw_total.increment();
                String methodArgs = getMethodArgs(joinPoint);
                log.info("接口抛异常方法名[{}]，所传参数[{}]",classMethod,methodArgs);
                return getThrowDetail(e);
            }
        });
        return result;
    }
 
    /**
     * 获取自定义异常的详情
     * @param e
     * @return
     */
    private ResponseResult getThrowDetail(Throwable e) {
        /*if(e instanceof BizException){
            BizException eb = (BizException) e;
            log.info("业务异常: {} {}", eb.getErrorEnum().getCode(), eb.getErrorEnum().getMsg());
            return new ResponseResult(eb.getErrorEnum());
        }else if(e instanceof ErrorException){
            ErrorException ee = (ErrorException) e;
            log.error("应用异常: {}", ee);
            return ResponseResult.error(ee.getErrorEnum());
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            // HttpRequestMethodNotSupportedException eh = (HttpRequestMethodNotSupportedException) e;
            log.error("不支持方法请求类型异常: {}", e);
            return ResponseResult.error(ResultCode.ACCOUNT_LOCKED);
        }else if(e instanceof DuplicateKeyException){
            log.error("唯一约束异常：{} {}",  e);
            return ResponseResult.error(ResultCode.DEVICE_BANNED);
        }else if(e instanceof BindException){
            BindException eb = (BindException) e;
            log.error("系统异常：{} {}" , e);
            if (eb.hasErrors()) {
                ObjectError objectError = eb.getAllErrors().get(0);
                String message = objectError.getDefaultMessage();
                return new ResponseResult(ResultCode.PARAM_ERROR).setMsg(message);
            }
            return ResponseResult.error();
        } else {
            log.error("系统异常： {}" , e);
            return ResponseResult.error();
        }*/
        log.error("系统异常： {}" , e);
        return ResponseResult.error();
 
    }
 
    // @AfterReturning(returning = "returnVal", pointcut = "pointCut()")
    public void doAftereReturning(Object returnVal){
        AtomicLong app_reponse_usedtime = null;
        app_reponse_usedtime = registry.gauge("pc_reponse_usedtime", new AtomicLong(0));
        log.info("开始时间：[{}]存的时间[{}]",startTime.get(),app_reponse_usedtime.get());
        app_reponse_usedtime.set((System.currentTimeMillis() - startTime.get()));
        System.out.println("请求执行时间：" + (System.currentTimeMillis() - startTime.get()));
    }
 
    /**
     * 接口抛异常时走这里
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "pointCut()" ,throwing = "exception")
    public void logTestAfterReturing3(JoinPoint joinPoint, Throwable exception){
        log.info("采集接口访问次数报错的接口名[{}],类名[{}]",joinPoint.getSignature().getName(),joinPoint.getSignature().getDeclaringTypeName());
        log.info("采集接口访问信息报错日志{}",exception.getStackTrace());
        exception.printStackTrace();
       /* if(exception instanceof NullPointerException){
            log.info("发生了空指针异常!!!!!");
        }*/
    }
}