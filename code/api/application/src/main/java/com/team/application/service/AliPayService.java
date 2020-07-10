package com.team.application.service;


import com.alipay.api.response.AlipayTradePayResponse;
import com.team.application.model.dto.PayDTO;

public interface AliPayService {

    AlipayTradePayResponse pay(String payNode, PayDTO dto);

}
