package com.czy.core.aop;

import com.czy.core.exception.ServiceException;
import com.czy.core.util.DateTimeUtil;
import com.czy.core.util.JwtUtil;
import com.czy.entity.po.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class AspectLog {

    private static Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Resource
    private LogQueue logQueue;

    /*
    定义切点
     */
    @Pointcut("@annotation(com.czy.core.aop.AnnotationLog)")
    public void methodCachePointcut() {

    }

    @Before("methodCachePointcut()")
    public void doBefore(JoinPoint point) throws Exception {
        Log log = getSystemLogInit(point);
        log.setLogType(Log.LogInfo);
        logQueue.add(log);
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
                Log log = getSystemLogInit(p);
                log.setLogType(Log.LogError);
                log.setExceptionCode(e.getClass().getName());
                log.setExceptionDetail(e.getMessage());
                logQueue.add(log);
            } catch (Exception ex) {
                logger.error("==异常通知异常==");
                logger.error("异常信息:{}", ex.getMessage());
            }
        }
    }

    private Log getSystemLogInit(JoinPoint p) {
        Log log = new Log();
        try {
            //类名
            String targetClass = p.getTarget().getClass().getSimpleName();
            //请求的方法名
            String tartgetMethod = p.getSignature().getName();
            log.setDescription(getMthodRemark(p));
            log.setMethod(targetClass + "->" + tartgetMethod);
            //大家可自行百度获取ip的方法
            log.setRequestIp("127.0.0.1");
            log.setUserId(JwtUtil.getCurrentUser().getUserId());
            log.setAddedTime(DateTimeUtil.getCurrentDateTime());
        } catch (Exception ex) {
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
        return log;
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
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methodRemark = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    AnnotationLog methodCache = m.getAnnotation(AnnotationLog.class);
                    if (methodCache != null) {
                        methodRemark = methodCache.remark();
                    }
                    break;
                }
            }
        }
        return methodRemark;
    }
}
