package com.team.stream.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@Configuration
public class StreamListenerConfig {

    @Bean
    public Consumer<Message<String>> rabbitInput11() {
        return t -> {
            Long deliveryTag = t.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
            Channel channel = t.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
            System.out.println(t);
            try {
                channel.basicAck(deliveryTag, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public Consumer<List<String>> rabbitInput12() {
        return list -> {
            list.forEach(System.out::println);
        };
    }

    @Bean
    public Consumer<String> kafkaInput21() {
        return t -> {
            System.out.println(t);
//            Long deliveryTag = t.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
//            Channel channel = t.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
//            System.out.println(t);
//            try {
//                channel.basicAck(deliveryTag, false);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        };
    }

}
