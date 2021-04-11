package com.team.fileresolve.receiver;

import com.alibaba.fastjson.JSONObject;
import com.team.application.ApplicationConfig;
import com.team.application.model.vo.MaterialVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FileResolveReceiver {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public SimpleRabbitListenerContainerFactory fileContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(10);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @RabbitListener(queues = ApplicationConfig.FILE_RESOLVE_QUEUE, containerFactory = "fileContainerFactory")
    public void receive(String message) {
        MaterialVO materialVO = JSONObject.parseObject(message, MaterialVO.class);
        System.out.println(message);
    }

}
