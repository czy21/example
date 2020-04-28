package com.team.application.queue;

import com.team.application.model.vo.AsyncVO;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RequestQueue {
    private BlockingQueue<AsyncVO<String, Object>> orderQueue = new LinkedBlockingQueue<>(50);

    public BlockingQueue<AsyncVO<String, Object>> getOrderQueue() {
        return orderQueue;
    }
}
