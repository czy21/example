package com.team.external;

import com.team.external.ali.model.AliConfig;
import com.team.external.ali.pay.AliPayConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Configuration
public class ExternalConfig {
    public ExternalConfig(AliConfig aliConfig) throws IOException {
        AliPayConfig payConfig = aliConfig.getPay();
        payConfig.setPublicKeyFile(IOUtils.toString(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + payConfig.getPublicKeyFile()), StandardCharsets.UTF_8));
        payConfig.setAppPrivateKeyFile(IOUtils.toString(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + payConfig.getAppPrivateKeyFile()), StandardCharsets.UTF_8));
        for (AliPayConfig.AppConfig val : payConfig.getApp().values()) {
            val.setServerUrl(Optional.ofNullable(val.getServerUrl()).orElse(payConfig.getServerUrl()));
            val.setPublicKeyFile(StringUtils.isEmpty(val.getPublicKeyFile()) ? payConfig.getPublicKeyFile() : IOUtils.toString(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + payConfig.getPublicKeyFile()), StandardCharsets.UTF_8));
            val.setAppPrivateKeyFile(StringUtils.isEmpty(val.getAppPrivateKeyFile()) ? payConfig.getAppPrivateKeyFile() : IOUtils.toString(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + payConfig.getAppPrivateKeyFile()), StandardCharsets.UTF_8));
        }
    }
}
