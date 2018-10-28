package com.czy.core.aop;

import com.czy.entity.po.Log;
import com.czy.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class LogConsumer implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(LogConsumer.class);

    @Resource
    private LogQueue auditLogQueue;

    @Resource
    private LogService logService;

    public static final int DEFAULT_BATCH_SIZE = 64;

    private int batchSize = DEFAULT_BATCH_SIZE;

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    private boolean active = true;

    @PostConstruct
    public void init() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @PreDestroy
    public void close() {
        active = false;
    }


    @Override
    public void run() {
        while (active) {
            execute();
        }
    }

    public void execute() {
        List<Log> logs = new ArrayList<>();
        try {
            int size = 0;
            while (size < batchSize) {
                Log log = auditLogQueue.poll();
                if (log == null) {
                    break;
                }
                logs.add(log);
                size++;
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage(), ex);
        }
        if (!logs.isEmpty()) {
            try {
                logService.insertByBatch(logs);
            } catch (Exception e) {
                logger.error("异常信息:{}", e.getMessage());
            }
        }

    }
}
