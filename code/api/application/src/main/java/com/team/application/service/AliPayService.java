package com.team.application.service;


import com.team.application.model.dto.PayRequest;
import com.team.application.model.dto.PayResponse;

public interface AliPayService {

    PayResponse pay(String payNode, PayRequest request);

    PayResponse preCreate(String payNode, PayRequest request);

}
