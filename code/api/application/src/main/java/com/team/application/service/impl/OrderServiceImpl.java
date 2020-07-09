package com.team.application.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
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
    public AlipayTradePayResponse pay(String qrCode) {
        return aliPayService.pay("sample1", System.currentTimeMillis() + "", qrCode);
    }

    @Override
    public AlipayResponse queryOrderHistory(Map<String, Object> content) {
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        request.setBizContent(objectMapper.writeValueAsString(content));
//        return alipayClient.execute(request);
        return null;
    }
}
