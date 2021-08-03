package com.team.application;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.backoff.FixedBackOff;

//@Configuration
//@EnableKafka
public class ApplicationConfig {

    public static final String SPI_FILE_TOPIC = "spiFileTopic";
    public static final String SPI_DATA_TOPIC = "spiDataTopic";

//    @Bean
//    NewTopic materialTopic() {
//        return TopicBuilder.name(SPI_FILE_TOPIC)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    NewTopic spiDataTopic() {
//        return TopicBuilder.name(SPI_DATA_TOPIC)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public SeekToCurrentErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
//        return new SeekToCurrentErrorHandler(
//                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
//    }
//
//    @Bean
//    public RecordMessageConverter converter() {
//        return new StringJsonMessageConverter();
//    }


}
