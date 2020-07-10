package com.team.application.service;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePayResponse;

import java.util.Map;

public interface OrderService {

    AlipayTradePayResponse pay(String authCode);

    AlipayResponse queryOrderHistory(Map<String, Object> content);
}
