package com.team.core.aop;

import com.team.core.exception.ServiceException;
import com.team.core.util.DateTimeUtil;
import com.team.core.util.HttpClientUtil;
import com.team.core.util.JwtUtil;
import com.team.entity.po.Log;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.net.InetAddress;

@Aspect
@Component
@Slf4j
public class AspectLog {
    @Resource
    private LogQueue logQueue;

    /*
    定义切点
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void methodCachePointcut() {

    }

    @Before("methodCachePointcut()")
    public void doBefore(JoinPoint point) throws Exception {
        Log sysLog = getSystemLogInit(point);
        sysLog.setLogType(Log.LogInfo);
        logQueue.add(sysLog);
    }

    /**
     * 调用后的异常处理
     *
     * @param p
     * @param e
     */
    @AfterThrowing(pointcut = "methodCachePointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint p, Throwable e) throws Throwable {
        //业务异常不用记录
        if (!(e instanceof ServiceException)) {
            try {
                Log sysLog = getSystemLogInit(p);
                sysLog.setLogType(Log.LogError);
                sysLog.setExceptionCode(e.getClass().getName());
                sysLog.setExceptionDetail(e.getMessage());
                logQueue.add(sysLog);
            } catch (Exception ex) {
                log.error("==异常通知异常==");
                log.error("异常信息:{}", ex.getMessage());
            }
        }
    }

    private Log getSystemLogInit(JoinPoint p) {
        Log sysLog = new Log();
        try {
            String targetClass = p.getTarget().getClass().getSimpleName();
            String tartgetMethod = p.getSignature().getName();
            sysLog.setDescription(getMthodRemark(p));
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

    /**
     * 获取方法的中文备注____用于记录用户的操作日志描述
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static String getMthodRemark(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methodRemark = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                ApiOperation methodCache = m.getAnnotation(ApiOperation.class);
                if (methodCache != null) {
                    methodRemark = methodCache.value();
                }
                break;
            }
        }
        return methodRemark;
    }
}
