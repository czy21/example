package com.team.portal.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.team.application.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(path = "order")
public class OrderController {


    @Resource
    OrderService orderService;

    @GetMapping(path = "pay")
    public AlipayOpenPublicTemplateMessageIndustryModifyResponse pay() throws AlipayApiException {
        return orderService.pay();
    }
}
