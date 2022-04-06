package com.team.application.config;

import com.czy.pulsar.producer.Producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class QueueConfig {

    public static final String SPI_FILE_TOPIC = "spiFileTopic";
    public static final String SPI_DATA_TOPIC = "spiDataTopic";
    public static final String DEMO_TOPIC_1 = "topic-demo-1";

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

    @Bean
    public Producer<Map<String, Object>> demoTopic() {
        return Producer.builder().topic(DEMO_TOPIC_1).create();
    }
//    @Bean
//    public ProducerFactory producerFactory() {
//        return new ProducerFactory()
//                .addProducer(DEMO_TOPIC_1, Map.class)
//                .addProducer(SPI_FILE_TOPIC, MaterialVO.class)
//                .addProducer(SPI_DATA_TOPIC, RowModel.class);
//    }
}
