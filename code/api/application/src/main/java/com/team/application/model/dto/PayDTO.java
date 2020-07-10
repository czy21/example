package com.team.application.model.dto;

import lombok.Data;

@Data
public class PayDTO {
    private String subject;
    private String outTradeNo;
    private String authCode;
    private String totalAmount;
}
