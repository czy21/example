package com.team.application.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.team.application.service.AliPayService;
import com.team.external.ali.model.AliConfig;
import com.team.external.ali.pay.AliPayConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AliPayServiceImpl implements AliPayService {

    private final Map<String, AlipayClient> aliPayClients = new HashMap<>();

    @Autowired
    AliConfig aliConfig;

    @Override
    public AlipayTradePayResponse pay(String payNode, String tradeCode, String qrCode) {
        AliPayConfig.AppConfig payAppConfig = aliConfig.getPay().getApp().get(payNode);
        if (StringUtils.isEmpty(payAppConfig.getPublicKeyFile())) {
            payAppConfig.setPublicKeyFile(aliConfig.getPay().getPublicKeyFile());
        }
        if (StringUtils.isEmpty(payAppConfig.getAppPrivateKeyFile())) {
            payAppConfig.setAppPrivateKeyFile(aliConfig.getPay().getAppPrivateKeyFile());
        }
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(tradeCode);
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("100");
        model.setAuthCode(qrCode);
        model.setScene(payAppConfig.getScene());
        request.setBizModel(model);
        try {
            return getAliPayClient(payNode, payAppConfig).execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public AlipayClient getAliPayClient(String payNode, AliPayConfig.AppConfig appConfig) {
        AlipayClient client;
        synchronized (aliPayClients) {
            client = aliPayClients.get(payNode);
            if (client == null) {
                client = new DefaultAlipayClient(
                        "https://openapi.alipaydev.com/gateway.do",
                        appConfig.getAppId(),
                        appConfig.getAppPrivateKeyFile(),
                        "json",
                        "utf-8",
                        appConfig.getPublicKeyFile(), "RSA2");
                aliPayClients.put(payNode, client);
            }
        }
        return client;
    }

}
