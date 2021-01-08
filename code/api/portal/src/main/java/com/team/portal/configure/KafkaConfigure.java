package com.team.portal.configure;


import com.team.portal.kafka.MessageEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
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
    NewTopic topic() {
        return new NewTopic("confirm-topic", 1, (short) 1);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public KStream<String, MessageEvent> kStream(StreamsBuilder streamsBuilder) { //2
        KStream<String, MessageEvent> stream = streamsBuilder.stream("my-topic");//3
        stream.map((key, value) -> {
            value.setName(value.getName().toUpperCase());
            return new KeyValue<>(key, value); //4
        }).to("another-topic"); //5
        return stream;
    }

}
