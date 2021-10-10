package com.team.application.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String SPI_FILE_TOPIC = "spiFileTopic";
    public static final String SPI_DATA_TOPIC = "spiDataTopic";
    public static final String ThroughputTest1_TOPIC = "ThroughputTest1";

    @Bean
    public Queue spiFileTopic() {
        return new Queue(SPI_FILE_TOPIC, true);
    }

    @Bean
    public Queue spiDataTopic() {
        return new Queue(SPI_DATA_TOPIC, true);
    }

    @Bean
    public Queue ThroughputTest1(){return new Queue(ThroughputTest1_TOPIC, true);}
}
