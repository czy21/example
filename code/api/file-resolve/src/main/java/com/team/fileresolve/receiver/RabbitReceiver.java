package com.team.fileresolve.receiver;

import com.alibaba.excel.EasyExcel;
import com.team.application.config.QueueConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.listener.FileListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
@Component
public class RabbitReceiver {

    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    FileColumnMappingRepository fileColumnMappingRepository;

    @RabbitListener(queues = QueueConfig.SPI_FILE_TOPIC)
    public void receive(MaterialVO materialVO) throws IOException {
        MaterialEntity materialEntity = materialMapper.selectById(materialVO.getUid());
        File f = Paths.get(materialEntity.getMaterialTarget().getRootUrl(), materialEntity.getMaterialTarget().getRootPath(), materialEntity.getPath()).toFile();
        EasyExcel.read(f, new FileListener(rabbitTemplate, fileColumnMappingRepository)).sheet().doRead();
    }


}
