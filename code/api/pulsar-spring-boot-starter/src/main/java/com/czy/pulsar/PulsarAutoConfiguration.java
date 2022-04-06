package com.czy.pulsar;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.auth.oauth2.AuthenticationFactoryOAuth2;
import org.apache.pulsar.shade.com.google.common.base.Strings;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties({PulsarProperties.class})
public class PulsarAutoConfiguration {

    PulsarProperties pulsarProperties;

    public PulsarAutoConfiguration(PulsarProperties pulsarProperties,
                                   ObjectProvider<Producer<?>>) {
        this.pulsarProperties = pulsarProperties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "pulsarClient")
    public PulsarClient pulsarClient() throws PulsarClientException {
        ClientBuilder pulsarClientBuilder = PulsarClient.builder()
                .serviceUrl(pulsarProperties.getServiceUrl())
                .ioThreads(pulsarProperties.getIoThreads())
                .listenerThreads(pulsarProperties.getListenerThreads())
                .enableTcpNoDelay(pulsarProperties.isEnableTcpNoDelay())
                .keepAliveInterval(pulsarProperties.getKeepAliveIntervalSec(), TimeUnit.SECONDS)
                .connectionTimeout(pulsarProperties.getConnectionTimeoutSec(), TimeUnit.SECONDS)
                .operationTimeout(pulsarProperties.getOperationTimeoutSec(), TimeUnit.SECONDS)
                .startingBackoffInterval(pulsarProperties.getStartingBackoffIntervalMs(), TimeUnit.MILLISECONDS)
                .maxBackoffInterval(pulsarProperties.getMaxBackoffIntervalSec(), TimeUnit.SECONDS);
        return pulsarClientBuilder.build();
    }

}
