package com.team.application.queue;

import com.team.application.model.vo.AsyncVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class OrderTask extends Thread {

    @Autowired
    private RequestQueue queue;

    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                AsyncVO<String, Object> vo = queue.getOrderQueue().take();
                System.out.println("[ OrderTask ]开始处理订单");
                String params = vo.getParams();
                TimeUnit.SECONDS.sleep(10);
                Map<String, Object> map = new HashMap<>();
                map.put("params", params);
                map.put("time", System.currentTimeMillis());
                vo.getResult().setResult(map);
                System.out.println("[ OrderTask ]订单处理完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
