package com.team.application.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.team.application.model.automap.PayAutoMap;
import com.team.application.model.dto.PayRequest;
import com.team.application.model.dto.PayResponse;
import com.team.application.service.AliPayService;
import com.team.external.ali.model.AliConfig;
import com.team.external.ali.pay.AliPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AliPayServiceImpl implements AliPayService {

    private final Map<String, AlipayClient> aliPayClients = new HashMap<>();

    @Autowired
    AliConfig aliConfig;

    @Autowired
    PayAutoMap payAutoMap;

    @Override
    public PayResponse pay(String payNode, PayRequest dto) {
        AliPayConfig.AppConfig appConfig = aliConfig.getPay().getApp().get(payNode);

        AlipayTradePayModel model = payAutoMap.mapToModel(dto);
        model.setScene(appConfig.getScene());

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);
        try {
            return payAutoMap.mapToResponse(acquireAliPayClient(payNode, appConfig).execute(request));
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PayResponse preCreate(String payNode, PayRequest request) {
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        return null;
    }

    public AlipayClient acquireAliPayClient(String payNode, AliPayConfig.AppConfig appConfig) {
        AlipayClient client;
        synchronized (aliPayClients) {
            client = aliPayClients.get(payNode);
            if (client == null) {
                client = new DefaultAlipayClient(
                        appConfig.getServerUrl(),
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
