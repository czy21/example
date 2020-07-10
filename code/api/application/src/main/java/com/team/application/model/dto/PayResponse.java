package com.team.application.model.dto;

import lombok.Data;

@Data
public class PayResponse {
    private String tradeNo;
    private String tradePlatformNo;
    private String totalAmount;
}
