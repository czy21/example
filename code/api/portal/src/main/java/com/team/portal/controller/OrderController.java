package com.team.portal.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.team.application.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(path = "order")
public class OrderController {


    @Resource
    OrderService orderService;

    @GetMapping(path = "pay")
    public AlipayTradePayResponse pay(@RequestParam("authCode") String authCode) {
        return orderService.pay(authCode);
    }

    @PostMapping(path = "tradeQuery")
    public AlipayResponse tradeQuery(@RequestBody Map<String, Object> content) {
        return orderService.queryOrderHistory(content);
    }

}
