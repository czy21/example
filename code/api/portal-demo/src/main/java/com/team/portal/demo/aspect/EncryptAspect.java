package com.team.portal.demo.aspect;

import com.team.portal.demo.annotation.Encrypt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncryptAspect {

    @Before("@annotation(a)")
    public void before(JoinPoint joinPoint, Encrypt a) {

    }
}
