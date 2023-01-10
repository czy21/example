package com.team.portal.controller;

import com.alipay.api.AlipayResponse;
import com.team.application.model.dto.PayResponse;
import com.team.application.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "order")
public class OrderController {


    @Resource
    OrderService orderService;

    @GetMapping(path = "pay")
    public PayResponse pay(@RequestParam("authCode") String authCode) {
        return orderService.pay(authCode);
    }

    @PostMapping(path = "tradeQuery")
    public AlipayResponse tradeQuery(@RequestBody Map<String, Object> content) {
        return orderService.queryOrderHistory(content);
    }

}
