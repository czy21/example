package com.team.portal.configure;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Locale;

@Configuration
@EnableKafkaStreams
@EnableScheduling
public class KafkaConfigure {
    @Bean
    NewTopic topic() {
        return new NewTopic("confirm-topic", 1, (short) 1);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {

        KStream<String, String> textLine = builder.stream("my-topic");

        KStream<String, String> stream = textLine
                .flatMapValues(v -> Arrays.asList(v.toLowerCase(Locale.getDefault()).split(" ")))
                .groupBy((k, v) -> v)
                .count()
                .mapValues((k, v) -> v.toString())
                .toStream();
        stream.to("another-topic", Produced.with(Serdes.String(), Serdes.String()));

        return stream;
    }

}
