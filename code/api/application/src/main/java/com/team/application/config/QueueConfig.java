package com.team.application.config;


import com.czy.learning.pulsar.core.ProducerBuilderWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import org.apache.pulsar.client.api.Schema;
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
    public ProducerBuilderWrapper demoMapProducer() throws Exception {
        return (client) -> client.newProducer(Schema.JSON(Map.class)).topic(DEMO_TOPIC_1);
    }

    @Bean
    public ProducerBuilderWrapper spiFileProducer() throws Exception {
        return client -> client.newProducer(Schema.JSON(MaterialVO.class)).topic(SPI_FILE_TOPIC);
    }

    @Bean
    public ProducerBuilderWrapper spiDataProducer() throws Exception {
        return client -> client.newProducer(Schema.JSON(RowModel.class)).topic(SPI_DATA_TOPIC);
    }

}
