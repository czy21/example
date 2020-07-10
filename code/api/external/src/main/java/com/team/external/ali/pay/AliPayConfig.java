package com.team.external.ali.pay;

import lombok.Data;

import java.util.Map;

@Data
public class AliPayConfig {
    private String publicKeyFile;
    private String appPrivateKeyFile;
    private String serverUrl;
    private Map<String, AppConfig> app;

    @Data
    public static class AppConfig {
        private String appId;
        private String scene;
        private String publicKeyFile;
        private String appPrivateKeyFile;
        private String serverUrl;
    }
}

