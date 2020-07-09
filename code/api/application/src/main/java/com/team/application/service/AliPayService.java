package com.team.application.service;


import com.alipay.api.response.AlipayTradePayResponse;

public interface AliPayService {

    AlipayTradePayResponse pay(String payNode,String tradeCode, String qrCode);

}
