package com.team.provider;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectResponse {


    @Pointcut("execution(* com.softium.xsk.core.provider.DataResponse+.*())")
    public void executeResponseImpl(){

    }




}
