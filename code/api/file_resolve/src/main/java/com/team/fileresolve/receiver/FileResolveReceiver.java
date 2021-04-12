package com.team.fileresolve.receiver;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.team.application.ApplicationConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.application.util.MaterialUtil;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.listener.FileListener;
import org.apache.catalina.mapper.MapperListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

@Component
public class FileResolveReceiver {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    private RedisTemplate<String, Map<String, Object>> stringListRedisTemplate;
    @Autowired
    FileColumnMappingRepository fileColumnMappingRepository;

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
        MaterialEntity materialEntity = materialMapper.selectById(materialVO.getUid());
        File f = Path.of(materialEntity.getMaterialTarget().getRootUrl(), materialEntity.getMaterialTarget().getRootPath(), materialEntity.getPath()).toFile();
        EasyExcel.read(f, new FileListener(stringListRedisTemplate, fileColumnMappingRepository)).sheet().doRead();

    }

}
