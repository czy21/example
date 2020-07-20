package com.team.infrastructure.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataLockAop {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(dataLock)")
    public Object around(ProceedingJoinPoint point, DataLock dataLock) {

        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String key = parseKey(dataLock.value(), method, args);
        DataResolver dataResolver = new DefaultDataResolver();
        RedisLock redisLock = new RedisLock("manualRinse", key, redisTemplate, dataResolver);
        boolean isLock = redisLock.lock();
        if (isLock) {
            try {
                return point.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            } finally {
                redisLock.unlock();
            }
        }
        return null;
    }

    private String parseKey(String key, Method method, Object[] args) {
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        if (paraNameArr == null) {
            return null;
        }
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
