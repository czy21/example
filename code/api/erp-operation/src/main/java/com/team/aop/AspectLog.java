package com.team.aop;

import com.team.core.annotations.NoLog;
import com.team.exception.ServiceException;
import com.team.util.DateTimeUtil;
import com.team.util.HttpClientUtil;
import com.team.util.JwtUtil;
import com.team.entity.system.Log;
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

    @Pointcut("execution(* com.team.controller..*.*(..))")
    public void methodCachePointcut() {

    }

    @Before("methodCachePointcut()")
    public void doBefore() {
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning("methodCachePointcut()")
    public void doAfterReturning(JoinPoint point) {
        if (!isNoLog(point)) {
            Log sysLog = getSystemLogInit(point);
            sysLog.setLogType(Log.logInfo);
            sysLog.setSpendTime((int) (System.currentTimeMillis() - startTime.get()));
            logQueue.add(sysLog);
        }
    }

    @AfterThrowing(pointcut = "methodCachePointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint p, Throwable e) {
        if (!(e instanceof ServiceException)) {
            try {
                Log sysLog = getSystemLogInit(p);
                sysLog.setLogType(Log.logError);
                sysLog.setExceptionCode(e.getClass().getName());
                sysLog.setExceptionDetail(e.getMessage());
                logQueue.add(sysLog);
            } catch (Exception ex) {
                log.error("==异常通知异常==");
                log.error("异常信息:{}", ex.getMessage());
            }
        }
    }

    public Boolean isNoLog(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        NoLog logAttr = method.getAnnotation(NoLog.class);
        return logAttr != null;
    }

    private Log getSystemLogInit(JoinPoint p) {
        Log sysLog = new Log();
        try {
            String targetClass = p.getTarget().getClass().getSimpleName();
            String tartgetMethod = p.getSignature().getName();
            sysLog.setDescription(getMethodRemark(p));
            sysLog.setMethod(targetClass + "->" + tartgetMethod);
            sysLog.setRequestIp(HttpClientUtil.getClientIp());
            sysLog.setUserId(JwtUtil.getCurrentUser().getUserId());
            sysLog.setAddedTime(DateTimeUtil.getCurrentDateTime());
        } catch (Exception ex) {
            log.error("==异常通知异常==");
            log.error("异常信息:{}", ex.getMessage());
        }
        return sysLog;
    }

    private static String getMethodRemark(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ApiOperation methodCache = method.getAnnotation(ApiOperation.class);
        return methodCache.value();
    }
}
