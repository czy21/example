package com.team.fileresolve.receiver;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.config.QueueConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.application.util.MaterialUtil;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.listener.FileListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Component
public class KafkaReceiver {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    @Autowired
    FileColumnMappingRepository fileColumnMappingRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public ConsumerFactory<String, MaterialVO> consumerFactory(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
                                                               @Value("${spring.kafka.consumer.group-id}") String consumerGroupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(MaterialVO.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MaterialVO>> kafkaListenerContainerFactory(
            ConsumerFactory<String, MaterialVO> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, MaterialVO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);
        return factory;
    }

    @KafkaListener(topics = QueueConfig.SPI_FILE_TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public void materialTopicReceive(MaterialVO materialvo) throws IOException {
        MaterialEntity materialEntity = materialMapper.selectById(materialvo.getUid());
        File f = MaterialUtil.getFile(materialEntity.getPath(), materialEntity.getMaterialTarget().getRootPath());
        EasyExcel.read(f, new FileListener(kafkaTemplate, fileColumnMappingRepository)).sheet().doRead();
    }


}
