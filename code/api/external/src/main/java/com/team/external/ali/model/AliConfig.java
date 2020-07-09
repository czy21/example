package com.team.external.ali.model;

import com.team.external.ali.pay.AliPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "ali")
public class AliConfig {
    private AliAccessKeyConfig accessKey;
    private AliPayConfig pay;
    private Map<String, Object> oss;
    private Map<String, Object> sms;
}
