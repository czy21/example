package com.team.webdav.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
@RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigConfigurationProperties {
    private String name;

    private String name1;
}
