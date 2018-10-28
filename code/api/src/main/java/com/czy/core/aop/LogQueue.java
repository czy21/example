package com.czy.core.aop;

import com.czy.entity.po.Log;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class LogQueue {
    private BlockingQueue<Log> blockingQueue = new LinkedBlockingQueue<>();

    public void add(Log log) {
        blockingQueue.add(log);
    }

    public Log poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}
