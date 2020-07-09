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
        AliPayConfig payConfig = aliConfig.getPay();
        AliPayConfig.AppConfig appConfig = aliConfig.getPay().getApp().get(payNode);
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(tradeCode);
        model.setSubject("Iphone6 16G");
        model.setTotalAmount("100");
        model.setAuthCode(qrCode);
        model.setScene(appConfig.getScene());
        request.setBizModel(model);
        try {
            return getAliPayClient(payNode, payConfig, appConfig).execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public AlipayClient getAliPayClient(String payNode, AliPayConfig payConfig, AliPayConfig.AppConfig appConfig) {
        AlipayClient client;
        synchronized (aliPayClients) {
            client = aliPayClients.get(payNode);
            if (client == null) {
                client = new DefaultAlipayClient(
                        "https://openapi.alipaydev.com/gateway.do",
                        appConfig.getAppId(),
                        StringUtils.isEmpty(appConfig.getAppPrivateKeyFile()) ? payConfig.getAppPrivateKeyFile() : "",
                        "json",
                        "utf-8",
                        StringUtils.isEmpty(appConfig.getPublicKeyFile()) ? payConfig.getPublicKeyFile() : "", "RSA2");
                aliPayClients.put(payNode, client);
            }
        }
        return client;
    }

}
