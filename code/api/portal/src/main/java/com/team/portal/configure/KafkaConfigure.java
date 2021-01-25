package com.team.portal.configure;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableKafkaStreams
@EnableScheduling
public class KafkaConfigure {
    @Bean
    NewTopic confirmTopic() {
        return new NewTopic("confirm-topic", 1, (short) 1);
    }

    @Bean
    NewTopic myTopic() {
        return new NewTopic("my-topic", 1, (short) 1);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public KStream<String, Long> kStream(StreamsBuilder builder) {

        KStream<String, String> textLine = builder.stream("my-topic");

        KStream<String, Long> stream = textLine
                .groupBy((k, v) -> v)
                .count(Materialized.as("word-count"))
                .toStream();
        stream.to("another-topic", Produced.with(Serdes.String(), Serdes.Long()));
        return stream;
    }

}
