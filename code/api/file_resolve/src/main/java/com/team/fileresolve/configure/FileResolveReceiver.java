package com.team.fileresolve.configure;

import com.team.application.ApplicationConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FileResolveReceiver {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> fileResolveListenerContainerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        factory.setPrefetchCount(2);
        return factory;
    }

    @RabbitListener(queues = ApplicationConfig.FILE_RESOLVE_QUEUE, containerFactory = "fileResolveListenerContainerFactory")
    public void receive(String message) {
        System.out.println(message);
    }

}
