package com.team.fileresolve.receiver;

import com.alibaba.excel.EasyExcel;
import com.team.application.config.QueueConfig;
import com.team.application.model.vo.MaterialVO;
import com.team.application.util.MaterialUtil;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.fileresolve.listener.FileListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class RabbitReceiver {

    @Autowired
    MaterialMapper materialMapper;

    @RabbitListener(queues = QueueConfig.SPI_FILE_TOPIC)
    public void receive(MaterialVO materialVO) {
        System.out.println("aaa");
//        MaterialEntity materialEntity = materialMapper.selectById(materialvo.getUid());
//        File f = MaterialUtil.getFile(materialEntity.getPath(), materialEntity.getMaterialTarget().getRootPath());
//        EasyExcel.read(f, new FileListener(kafkaTemplate, fileColumnMappingRepository)).sheet().doRead();
    }
}
