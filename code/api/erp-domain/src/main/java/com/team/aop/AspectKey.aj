package com.team.aop;


import com.baidu.fsg.uid.UidGenerator;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.PrimaryKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;

@Aspect
@Component
@Slf4j
public class AspectKey {


    @Pointcut("@annotation(com.team.entity.PrimaryKeyGenerator)")
    public void auditAspect() {

    }

//    @Pointcut("@annotation(com.team.entity.PrimaryKeyGenerator)")
//    public void auditAspect() {
//
//    }

    @Around(value = "auditAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
    }

    private Serializable getPrimaryKey(Object entity, Field field) {
        if (!field.isAnnotationPresent(TableId.class) || field.isAnnotationPresent(Id.class)) {
            return null;
        }
        PrimaryKeyGenerator primaryKeyGenerator = field.getAnnotation(PrimaryKeyGenerator.class);
        if (primaryKeyGenerator == null) {
            return null;
        }
        Class<? extends UidGenerator> clazz = primaryKeyGenerator.keyGeneratorClass();
        Assert.notNull(clazz, "keyGeneratorClass can't null");
        try {
            UidGenerator uidGenerator = clazz.newInstance();


        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
