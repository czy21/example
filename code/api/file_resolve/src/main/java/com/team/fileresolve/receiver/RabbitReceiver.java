package com.team.fileresolve.receiver;

import com.team.application.config.QueueConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.domain.mapper.MaterialMapper;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @Autowired
    MaterialMapper materialMapper;

    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory singleListenerContainer(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    @RabbitListener(queues = QueueConfig.SPI_FILE_TOPIC,containerFactory = "singleListenerContainer")
    public void receive(MaterialVO materialVO) {
//        System.out.println("aaa");
        System.out.println(materialVO);
//        MaterialEntity materialEntity = materialMapper.selectById(materialvo.getUid());
//        File f = MaterialUtil.getFile(materialEntity.getPath(), materialEntity.getMaterialTarget().getRootPath());
//        EasyExcel.read(f, new FileListener(kafkaTemplate, fileColumnMappingRepository)).sheet().doRead();
    }
}
