package com.team.application.queue;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
