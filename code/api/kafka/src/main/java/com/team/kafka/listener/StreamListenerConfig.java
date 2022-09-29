package com.team.kafka.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class StreamListenerConfig {

    @KafkaListener(topics = {"input4Topic"},groupId = "stream1")
    public void consume(String msg) {
        System.out.println(msg);
    }

//    @Bean
//    public Consumer<String> input3() {
//        return t -> {
//            System.out.println(t);
////            Long deliveryTag = t.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
////            Channel channel = t.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
////            System.out.println(t);
////            try {
////                channel.basicAck(deliveryTag, false);
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
//        };
//    }

}
