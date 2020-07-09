package com.team.external.ali.pay;

import lombok.Data;

import java.util.Map;

@Data
public class AliPayConfig {
    private KeyFile keyFile;
    private Map<String, AliPayConfig> app;

    @Data
    public static class AppConfig {
        private String appId;
        private String scene;
        private KeyFile keyFile;
    }

    @Data
    public static class KeyFile {
        private String publicKeyFile;
        private String appPrivateKeyFile;
    }
}

