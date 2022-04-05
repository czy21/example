package com.team.fileresolve.receiver;

import com.team.application.config.QueueConfig;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PulsarReceiver {
    @PulsarConsumer(topic = QueueConfig.DEMO_TOPIC_1, clazz = Map.class)
    public void consume(Map<String, Object> message) {
        System.out.println(message);
    }
}
