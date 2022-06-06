package com.team.fileresolve.receiver;

import com.rabbitmq.client.Channel;
import com.team.application.automap.RowAutoMap;
import com.team.application.config.QueueConfig;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.service.AbstractSPIQueueService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RabbitSPIReceiver extends AbstractSPIQueueService {

    RabbitTemplate rabbitTemplate;

    public RabbitSPIReceiver(RowAutoMap rowAutoMap,
                             FileColumnMappingRepository fileColumnMappingRepository,
                             MaterialMapper materialMapper,
                             MaterialService materialService,
                             SqlSessionFactory sqlSessionFactory,
                             RabbitTemplate rabbitTemplate) {
        super(rowAutoMap, fileColumnMappingRepository, materialMapper, materialService, sqlSessionFactory);
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory batchListenerFactory(ConnectionFactory connectionFactory,
                                                                     @Qualifier("jsonMessageConverter") MessageConverter messageConverter,
                                                                     @Value("${spi.batch-size}") Integer batchSize,
                                                                     @Value("${spi.consumer-size}") Integer consumerSize) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setBatchListener(true);
        factory.setConsumerBatchEnabled(true);
        factory.setBatchSize(batchSize);
        factory.setConcurrentConsumers(consumerSize);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @RabbitListener(queues = QueueConfig.SPI_FILE_TOPIC, concurrency = "3", ackMode = "MANUAL")
    public void listenerFile(MaterialVO materialVO, Message message, Channel channel) throws Exception {
        super.resolveFile(materialVO);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @SneakyThrows
    @RabbitListener(queues = QueueConfig.SPI_DATA_TOPIC, containerFactory = "batchListenerFactory")
    public void listenerRow(List<RowModel> rows) {
        super.consumeRow(rows);
    }

    public void produceRow(String topic, RowModel rowData) {
        rabbitTemplate.convertAndSend(topic, rowData);
    }
}
