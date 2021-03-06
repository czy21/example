package com.team.application.schedule;

import com.team.application.service.PersistService;
import com.team.application.service.SaleService;
import com.team.domain.dto.TaskDo;
import com.team.domain.entity.TaskEntity;
import com.team.domain.entity.TaskLogEntity;
import com.team.domain.kind.TaskStatusKind;
import com.team.domain.mapper.TaskLogMapper;
import com.team.domain.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class MigrateSchedule {

    Map<String, PersistService> persistServiceMap = new HashMap<>();
    @Autowired
    SaleService saleService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    TaskLogMapper taskLogMapper;

    public MigrateSchedule(ObjectProvider<PersistService[]> persistServices) throws Exception {
        for (var i : Optional.ofNullable(persistServices.getIfAvailable()).orElse(new PersistService[]{})) {
            Object obj;
            if (AopUtils.isAopProxy(i)) {
                obj = ((Advised) i).getTargetSource().getTarget();
            } else {
                obj = i;
            }
            if (obj != null) {
                persistServiceMap.put(obj.getClass().getSimpleName(), i);
            }
        }
    }

//    @Scheduled(fixedDelay = 5000)
    public void migrateJob() {
        TaskEntity queryTask = new TaskEntity();
        queryTask.setStatus(TaskStatusKind.STARTED.name());
        List<TaskDo> taskResult = taskMapper.selectList(queryTask);
        if (taskResult.size() > 0) {
            var p = taskResult.get(0);
            TaskEntity codeBatch = TaskEntity.of(p.getCode(), p.getBatchId());

            PersistService service = persistServiceMap.get(p.getCode());
            try {
                if (p.getFinishedCount() < p.getExecuteCount()) {
                    var loop = IntStream.rangeClosed(1, (p.getExecuteCount() - p.getFinishedCount()));
                    codeBatch.setStatus(TaskStatusKind.EXECUTING.name());
                    taskMapper.updateOne(codeBatch);
                    for (String i : loop.mapToObj(Integer::toString).collect(Collectors.toList())) {
                        if (taskMapper.selectOne(codeBatch).getStatus().equals(TaskStatusKind.TERMINATED.name())) {
                            break;
                        }
                        saleService.migrateToPersist(service);
                        TaskLogEntity tlEntity = new TaskLogEntity();
                        tlEntity.setCode(p.getCode());
                        tlEntity.setBatchId(p.getBatchId());
                        taskLogMapper.insert(tlEntity);
                        codeBatch.setStatus(null);
                    }
                }
            } catch (Exception e) {
                codeBatch.setStatus(TaskStatusKind.FAIL.name());
                taskMapper.updateOne(codeBatch);
                throw e;
            }
            codeBatch.setStatus(TaskStatusKind.FINISHED.name());
            taskMapper.updateOne(codeBatch);
        }
        log.info("migrate task ===========================================================================");
    }
}
