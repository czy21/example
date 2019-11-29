package com.team.operation.aop;

import com.team.application.core.annotations.NoLog;
import com.team.domain.entity.LogEntity;
import com.team.application.exception.ServiceException;
import com.team.application.util.DateTimeUtil;
import com.team.application.util.HttpClientUtil;
import com.team.application.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AspectLog {
    @Resource
    private LogQueue logQueue;

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.team.operation.controller..*.*(..))")
    public void excudeController() {

    }

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void apiAnnotation() {

    }

    /*
     * @author 陈昭宇
     * @description 方法执行前调用
     * @date 2018/12/27 16:13
     * @param []
     * @return void
     */
    @Before("excudeController()&&apiAnnotation()")
    public void doBefore() {
        startTime.set(System.currentTimeMillis());
    }

    /*
     * @author 陈昭宇
     * @description 方法执行成功与否都会调用
     * @date 2018/12/27 16:13
     * @param [joinPoint]
     * @return void
     */
    @After("excudeController()&&apiAnnotation()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (!isNoLog(method) && isApiOperation(method)) {
            LogEntity sysLog = getSystemLogInit(joinPoint);
            sysLog.setLogType(LogEntity.logInfo);
            sysLog.setSpendTime((int) (System.currentTimeMillis() - startTime.get()));
            logQueue.add(sysLog);
        }
    }

    /*
     * @author 陈昭宇
     * @description 方法异常后调用
     * @date 2018/12/27 16:12
     * @param [p, e]
     * @return void
     */
    @AfterThrowing(pointcut = "excudeController()&&apiAnnotation()", throwing = "e")
    public void doAfterThrowing(JoinPoint p, Throwable e) {
        if (!(e instanceof ServiceException)) {
            Method method = ((MethodSignature) p.getSignature()).getMethod();
            try {
                if (!isNoLog(method) && isApiOperation(method)) {
                    LogEntity sysLog = getSystemLogInit(p);
                    sysLog.setLogType(LogEntity.logError);
                    sysLog.setExceptionCode(e.getClass().getName());
                    sysLog.setExceptionDetail(e.getMessage());
                    sysLog.setSpendTime((int) (System.currentTimeMillis() - startTime.get()));
                    logQueue.add(sysLog);
                }
            } catch (Exception ex) {
                log.error("异常信息:{}", ex.getMessage());
            }
        }
    }


    /*
     * @author 陈昭宇
     * @description 判断是否有Nolog标识的Bean
     * @date 2018/12/27 16:09
     * @param [method]
     * @return java.lang.Boolean
     */
    private Boolean isNoLog(Method method) {
        return method.isAnnotationPresent(NoLog.class);
    }

    /*
     * @author 陈昭宇
     * @description 判断是否有Api标识的Bean
     * @date 2018/12/27 16:09
     * @param [method]
     * @return java.lang.Boolean
     */
    private Boolean isApiOperation(Method method) {
        return method.isAnnotationPresent(ApiOperation.class);
    }

    private String getMethodRemark(Method method) {
        return method.getAnnotation(ApiOperation.class).value();
    }

    /*
     * @author 陈昭宇
     * @description 获取被访问方法并创建日志信息
     * @date 2018/12/27 16:11
     * @param [joinPoint]
     * @return com.team.entity.mongo.Log
     */
    private LogEntity getSystemLogInit(JoinPoint joinPoint) {
        LogEntity sysLog = new LogEntity();
        try {
            String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            sysLog.setDescription(getMethodRemark(method));
            sysLog.setMethod(targetClassName + "->" + method.getName());
            sysLog.setRequestIp(HttpClientUtil.getClientIp());
            sysLog.setOperator(JwtUtil.getCurrentUser());
            sysLog.setCreatedDate(DateTimeUtil.getCurrentDateTime());
        } catch (Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
        }
        return sysLog;
    }
}
