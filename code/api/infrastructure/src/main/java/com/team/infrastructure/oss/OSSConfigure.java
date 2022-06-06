package com.team.infrastructure.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(OSSProperties.class)
public class OSSConfigure {

    @Bean
    public OSSClient ossClient(OSSProperties properties) {
        return new MinioOSSClient(properties.getMinio());
    }
}
