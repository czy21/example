package com.team.application.service;

import com.alipay.api.AlipayResponse;
import com.team.application.model.dto.PayResult;

import java.util.Map;

public interface OrderService {

    PayResult pay(String authCode);

    AlipayResponse queryOrderHistory(Map<String, Object> content);
}
