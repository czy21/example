package com.team.application.service;

import com.alipay.api.AlipayResponse;
import com.team.application.model.dto.PayResponse;

import java.util.Map;

public interface OrderService {

    PayResponse pay(String authCode);

    AlipayResponse queryOrderHistory(Map<String, Object> content);
}
