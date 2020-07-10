package com.team.application.service.impl;

import com.alipay.api.AlipayResponse;
import com.team.application.model.dto.PayRequest;
import com.team.application.model.dto.PayResponse;
import com.team.application.service.AliPayService;
import com.team.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    AliPayService aliPayService;

    @Override
    public PayResponse pay(String authCode) {
        PayRequest dto = new PayRequest();
        dto.setAuthCode(authCode);
        dto.setTradeNo(System.currentTimeMillis() + "");
        dto.setSubject("iphone 16G");
        dto.setTotalAmount("1000");
        return aliPayService.pay("sample1", dto);
    }

    @Override
    public AlipayResponse queryOrderHistory(Map<String, Object> content) {
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        request.setBizContent(objectMapper.writeValueAsString(content));
//        return alipayClient.execute(request);
        return null;
    }
}
