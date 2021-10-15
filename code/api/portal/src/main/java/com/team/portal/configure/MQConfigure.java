package com.team.portal.configure;


import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.team.application.config.QueueConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.application.util.MaterialUtil;
import com.team.domain.entity.MaterialEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.flexible.core.nodes.ProximityQueryNode;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableKafka
@Slf4j
public class MQConfigure {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;


    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = QueueConfig.ThroughputTest1_TOPIC, concurrency = "1", ackMode = "MANUAL")
    public void ThroughputTest1_TOPIConsumer(Map<String, Object> data, Message message, Channel channel) throws IOException {
//        messages.add(data);
        log.info(objectMapper.writeValueAsString(data));
//        log.info(""+messages.size());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @KafkaListener(topics = QueueConfig.SPI_FILE_TOPIC)
    public void materialTopicReceive(Object data) throws IOException {
        log.info(data.toString());
    }

}
