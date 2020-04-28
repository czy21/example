package com.team.application.controller;

import com.team.application.model.vo.AsyncVO;
import com.team.application.queue.RequestQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@RestController
@RequestMapping(path = "queue")
public class QueueController {
    @Autowired
    private RequestQueue queue;

    @PostMapping(path = "order")
    public DeferredResult<Object> order(@RequestBody Map<String, Object> param) throws InterruptedException {
        System.out.println("[ OrderController ] 接到下单请求");
        System.out.println("当前待处理订单数： " + queue.getOrderQueue().size());

        AsyncVO<String, Object> vo = new AsyncVO<>();
        DeferredResult<Object> result = new DeferredResult<>();

        vo.setParams(param.get("number").toString());
        vo.setResult(result);

        queue.getOrderQueue().put(vo);
        System.out.println("[ OrderController ] 返回下单结果");
        return result;
    }
}
