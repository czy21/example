package com.team.application.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class QueueListener {
    @Autowired
    private OrderTask orderTask;

    @PostConstruct
    public void init() {
        orderTask.start();
    }

    @PreDestroy
    public void destroy() {
        orderTask.setRunning(false);
    }
}
