package com.team.application.service;


import com.team.application.model.dto.PayDTO;
import com.team.application.model.dto.PayResult;

public interface AliPayService {

    PayResult pay(String payNode, PayDTO dto);

}
