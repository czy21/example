package com.team.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String SPI_FILE_TOPIC = "spiFileTopic";
    public static final String SPI_DATA_TOPIC = "spiDataTopic";

    @Bean
    public Queue spiFileTopic() {
        return new Queue(SPI_FILE_TOPIC, true);
    }

    @Bean
    public Queue spiDataTopic() {
        return new Queue(SPI_DATA_TOPIC, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
