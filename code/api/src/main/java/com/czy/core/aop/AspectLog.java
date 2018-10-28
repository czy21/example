package com.czy.core.aop;

import com.alibaba.fastjson.JSON;
import com.czy.core.exception.ServiceException;
import com.czy.core.util.DateTimeUtil;
import com.czy.entity.po.Log;
import com.czy.service.LogService;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AspectLog {

    private static Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Resource
    private LogService logService;

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
        logService.Insert(log);

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
                logService.Insert(log);
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
            String targetClass = p.getTarget().getClass().toString();
            //请求的方法名
            String tartgetMethod = p.getSignature().getName();
            //获取类名  UserController
            String classType = p.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            log.setDescription(getMthodRemark(p));
            log.setMethod(targetClass + "." + tartgetMethod);
            //大家可自行百度获取ip的方法
            log.setRequestIp("192.168.1.104");
//            log.setUserId(getUserId());
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
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    AnnotationLog methodCache = m.getAnnotation(AnnotationLog.class);
                    if (methodCache != null) {
                        methode = methodCache.remark();
                    }
                    break;
                }
            }
        }
        return methode;
    }

//    private static String getUserId() {
//        String userId = "";
//        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
//        if (userInfo != null) {
//            userId = userInfo.getId();
//        }
//        return userId;
//    }

}
