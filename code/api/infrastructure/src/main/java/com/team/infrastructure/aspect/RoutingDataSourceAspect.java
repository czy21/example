package com.team.infrastructure.aspect;

import com.team.infrastructure.annotation.DS;
import com.team.infrastructure.datasource.DynamicDataSourceContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoutingDataSourceAspect {

    @Around("@annotation(ds)")
    public Object switchDS(ProceedingJoinPoint joinPoint, DS ds) throws Throwable {
        String key = ds.value();
        DynamicDataSourceContext.put(key);
        return joinPoint.proceed();
    }
}
