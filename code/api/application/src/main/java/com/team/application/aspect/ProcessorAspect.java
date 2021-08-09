package com.team.application.aspect;

import com.team.application.annotation.ProcessMonitor;
import com.team.application.service.impl.SaleServiceImpl;
import com.team.domain.entity.MigratePerformanceLogEntity;
import com.team.domain.entity.SaleEntity;
import com.team.domain.mapper.MigratePerformanceLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.List;

@Component
@Aspect
@Slf4j
public class ProcessorAspect {

    @Autowired
    MigratePerformanceLogMapper migratePerformanceLogMapper;

    @Around(value = "@annotation(processMonitor) && args(maps,context)")
    public void doAround(ProceedingJoinPoint joinPoint, ProcessMonitor processMonitor, List<SaleEntity> maps, SaleServiceImpl.MigrateContext context) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        joinPoint.proceed();
        watch.stop();
        MigratePerformanceLogEntity migrateEntity = new MigratePerformanceLogEntity();
        migrateEntity.setTarget(joinPoint.getSignature().getDeclaringType().getSimpleName());
        migrateEntity.setCount(maps.size());
        migrateEntity.setDurationUnit("s");
        migrateEntity.setDuration(BigDecimal.valueOf(watch.getTotalTimeSeconds()));
        migrateEntity.setSequence(context.getSequence());
        migrateEntity.setBatchId(context.getBatchId());
        migratePerformanceLogMapper.insert(migrateEntity);
    }
}