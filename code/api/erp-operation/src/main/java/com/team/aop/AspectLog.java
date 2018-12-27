package com.team.aop;

import com.team.core.annotations.NoLog;
import com.team.exception.ServiceException;
import com.team.util.DateTimeUtil;
import com.team.util.HttpClientUtil;
import com.team.util.JwtUtil;
import com.team.entity.system.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.Join;
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

    @Pointcut("execution(* com.team.controller..*.*(..))")
    public void excudeController() {

    }

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void apiAnnotation() {

    }

    @Before("excudeController()&&apiAnnotation()")
    public void doBefore() {
        startTime.set(System.currentTimeMillis());
    }

    @After("excudeController()&&apiAnnotation()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (!isNoLog(method) && isApiOperation(method)) {
            Log sysLog = getSystemLogInit(joinPoint);
            sysLog.setLogType(Log.logInfo);
            sysLog.setSpendTime((int) (System.currentTimeMillis() - startTime.get()));
            logQueue.add(sysLog);
        }
    }

    @AfterThrowing(pointcut = "excudeController()&&apiAnnotation()", throwing = "e")
    public void doAfterThrowing(JoinPoint p, Throwable e) {
        if (!(e instanceof ServiceException)) {
            try {
                Log sysLog = getSystemLogInit(p);
                sysLog.setLogType(Log.logError);
                sysLog.setExceptionCode(e.getClass().getName());
                sysLog.setExceptionDetail(e.getMessage());
                logQueue.add(sysLog);
            } catch (Exception ex) {
                log.error("异常信息:{}", ex.getMessage());
            }
        }
    }

    private Boolean isNoLog(Method method) {
        return method.isAnnotationPresent(NoLog.class);
    }

    private Boolean isApiOperation(Method method) {
        return method.isAnnotationPresent(ApiOperation.class);
    }

    private String getMethodRemark(Method method) {
        return method.getAnnotation(ApiOperation.class).value();
    }

    private Log getSystemLogInit(JoinPoint joinPoint) {
        Log sysLog = new Log();
        try {
            String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            sysLog.setDescription(getMethodRemark(method));
            sysLog.setMethod(targetClassName + "->" + method.getName());
            sysLog.setRequestIp(HttpClientUtil.getClientIp());
            sysLog.setUserId(JwtUtil.getCurrentUser().getUserId());
            sysLog.setAddedTime(DateTimeUtil.getCurrentDateTime());
        } catch (Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
        }
        return sysLog;
    }
}
