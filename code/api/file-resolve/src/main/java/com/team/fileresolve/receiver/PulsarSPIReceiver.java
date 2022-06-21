package com.team.fileresolve.receiver;


import com.czy.learning.pulsar.annotation.PulsarListener;
import com.czy.learning.pulsar.core.PulsarTemplate;
import com.team.application.automap.RowAutoMap;
import com.team.application.config.QueueConfig;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.service.AbstractSPIQueueService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PulsarSPIReceiver extends AbstractSPIQueueService {

    PulsarTemplate pulsarTemplate;

    public PulsarSPIReceiver(RowAutoMap rowAutoMap, FileColumnMappingRepository fileColumnMappingRepository,
                             MaterialMapper materialMapper,
                             MaterialService materialService,
                             SqlSessionFactory sqlSessionFactory,
                             PulsarTemplate pulsarTemplate) {
        super(rowAutoMap, fileColumnMappingRepository, materialMapper, materialService, sqlSessionFactory);
        this.pulsarTemplate = pulsarTemplate;
    }

    @PulsarListener(subscriptionName = "file-resolve-group", topics = QueueConfig.SPI_FILE_TOPIC, clazz = MaterialVO.class, subscriptionType = SubscriptionType.Shared)
    //    @PulsarConsumer(topic = QueueConfig.SPI_FILE_TOPIC, clazz = MaterialVO.class, subscriptionType = {SubscriptionType.Shared})
    public void listenerFile(MaterialVO materialVO) throws Exception {
        super.resolveFile(materialVO);
    }

    //    @PulsarConsumer(topic = QueueConfig.SPI_DATA_TOPIC, clazz = RowModel.class, subscriptionType = {SubscriptionType.Shared})
//    public void listenerRow(RowModel row) {
//        super.consumeRow(List.of(row));
//    }
    @PulsarListener(subscriptionName = "file-resolve-group", topics = QueueConfig.SPI_DATA_TOPIC, clazz = RowModel.class, subscriptionType = SubscriptionType.Shared, batch = true)
    public void listenerRow(List<RowModel> rows) {
        super.consumeRow(rows);
    }

    @Override
    public void produceRow(String topic, RowModel rowData) throws Exception {
        pulsarTemplate.send(topic, rowData);
    }

}
