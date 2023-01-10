package com.team.application.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class QueueConfig {

    public static final String TOPIC_A = "topic-a";
    public static final String TOPIC_B = "topic-b";

    public static final String SPI_FILE_TOPIC = "spiFileTopic";
    public static final String SPI_DATA_TOPIC = "spiDataTopic";

    @Bean
    public Queue spiFileTopic() {
        return new Queue(SPI_FILE_TOPIC, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

//    @Bean
//    public ProducerBuilderWrapper topicAProducer() {
//        return client -> client.newProducer(Schema.JSON(Map.class)).topic(TOPIC_A);
//    }
//
//    @Bean
//    public ProducerBuilderWrapper topicBProducer() {
//        return client -> client.newProducer(Schema.JSON(Map.class)).topic(TOPIC_B);
//    }
//
//
//    @Bean
//    public ProducerBuilderWrapper spiFileProducer() {
//        return client -> client.newProducer(Schema.JSON(MaterialVO.class)).topic(SPI_FILE_TOPIC);
//    }
//
//    @Bean
//    public ProducerBuilderWrapper spiDataProducer() {
//        return client -> client.newProducer(Schema.JSON(RowModel.class)).topic(SPI_DATA_TOPIC);
//    }

}
