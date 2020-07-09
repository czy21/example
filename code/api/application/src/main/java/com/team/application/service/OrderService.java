package com.team.application.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface OrderService {

    AlipayTradePayResponse pay(String qrCode) throws AlipayApiException;

    AlipayResponse queryOrderHistory(Map<String, Object> content) throws AlipayApiException, JsonProcessingException;
}
