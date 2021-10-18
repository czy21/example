package com.team.fileresolve.receiver;

import com.team.application.config.QueueConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        log.info(materialVO.toString());
//        MaterialEntity materialEntity = materialMapper.selectById(materialVO.getUid());
//        File f = MaterialUtil.getFile(materialEntity.getPath(), materialEntity.getMaterialTarget().getRootPath());
//        EasyExcel.read(f, new FileListener(rabbitTemplate, fileColumnMappingRepository)).sheet().doRead();
    }


}
