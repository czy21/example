package com.team.operation.aop;

import com.team.domain.entity.LogEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class LogQueue {
    private BlockingQueue<LogEntity> blockingQueue = new LinkedBlockingQueue<>();

    public void add(LogEntity log) {
        blockingQueue.add(log);
    }

    public LogEntity poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}
