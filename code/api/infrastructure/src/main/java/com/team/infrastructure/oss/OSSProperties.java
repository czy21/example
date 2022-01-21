package com.team.infrastructure.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {

    private MinioProperties minio;

    @Data
    public static class MinioProperties {
        private String endpoint;
        private String accessKey;
        private String secretKey;
    }
}
