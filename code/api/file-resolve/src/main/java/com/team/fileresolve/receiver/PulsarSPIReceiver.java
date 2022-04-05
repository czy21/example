package com.team.fileresolve.receiver;

import com.team.application.automap.RowAutoMap;
import com.team.application.config.QueueConfig;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.domain.mapper.MaterialMapper;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.service.AbstractSPIQueueService;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PulsarSPIReceiver extends AbstractSPIQueueService {

    PulsarTemplate<RowModel> pulsarTemplate;

    public PulsarSPIReceiver(RowAutoMap rowAutoMap,
                             FileColumnMappingRepository fileColumnMappingRepository,
                             MaterialMapper materialMapper,
                             MaterialService materialService,
                             SqlSessionFactory sqlSessionFactory,
                             PulsarTemplate<RowModel> pulsarTemplate) {
        super(rowAutoMap, fileColumnMappingRepository, materialMapper, materialService, sqlSessionFactory);
        this.pulsarTemplate = pulsarTemplate;
    }

    @PulsarConsumer(topic = QueueConfig.SPI_FILE_TOPIC, clazz = MaterialVO.class)
    public void listenerFile(MaterialVO materialVO) throws Exception {
        super.resolveFile(materialVO);
    }

    @PulsarConsumer(topic = QueueConfig.SPI_DATA_TOPIC, clazz = RowModel.class, subscriptionType = SubscriptionType.Shared)
    public void listenerRow(RowModel row) {
        super.consumeRow(List.of(row));
    }

    @Override
    public void produceRow(String topic, RowModel rowData) throws Exception {
        pulsarTemplate.send(topic, rowData);
    }
}
