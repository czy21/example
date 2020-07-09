package com.team.external.ali.model;

import lombok.Data;

import java.util.Map;

@Data
public class AliConfig {
    private AliAccessKeyConfig accessKey;
    private Map<String, Object> pay;
    private Map<String, Object> oss;
    private Map<String, Object> sms;
}
