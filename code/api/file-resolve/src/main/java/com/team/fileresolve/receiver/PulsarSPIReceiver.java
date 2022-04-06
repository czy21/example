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
import io.github.majusko.pulsar.properties.PulsarProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.SchemaUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class PulsarSPIReceiver extends AbstractSPIQueueService implements InitializingBean {

    PulsarTemplate<RowModel> pulsarTemplate;
    PulsarClient pulsarClient;

    public PulsarSPIReceiver(RowAutoMap rowAutoMap,
                             FileColumnMappingRepository fileColumnMappingRepository,
                             MaterialMapper materialMapper,
                             MaterialService materialService,
                             SqlSessionFactory sqlSessionFactory,
                             PulsarTemplate<RowModel> pulsarTemplate,
                             PulsarClient pulsarClient) {
        super(rowAutoMap, fileColumnMappingRepository, materialMapper, materialService, sqlSessionFactory);
        this.pulsarTemplate = pulsarTemplate;
        this.pulsarClient = pulsarClient;
    }

    @PulsarConsumer(topic = QueueConfig.SPI_FILE_TOPIC, clazz = MaterialVO.class, subscriptionType = {SubscriptionType.Shared})
    public void listenerFile(MaterialVO materialVO) throws Exception {
        super.resolveFile(materialVO);
    }

//    @PulsarConsumer(topic = QueueConfig.SPI_DATA_TOPIC, clazz = RowModel.class, subscriptionType = {SubscriptionType.Shared})
//    public void listenerRow(RowModel row) {
//        super.consumeRow(List.of(row));
//    }

    @Override
    public void produceRow(String topic, RowModel rowData) throws Exception {
        pulsarTemplate.send(topic, rowData);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Consumer<RowModel> consumer = pulsarClient.newConsumer(Schema.JSON(RowModel.class))
                .topic(QueueConfig.SPI_DATA_TOPIC)
                .subscriptionName("demo")
                .subscriptionType(SubscriptionType.Shared)
                .consumerName("hello")
                .batchReceivePolicy(BatchReceivePolicy.builder()
                        .maxNumMessages(2000)
                        .maxNumBytes(20 * 1024 * 1024)
                        .timeout(100, TimeUnit.MILLISECONDS)
                        .build())
                .subscribe();
        new Thread(() -> {
            while (true) {
                try {
                    Messages<RowModel> message = consumer.batchReceive();
                    List<RowModel> rows = new ArrayList<>();
                    for (var m : message) {
                        rows.add(m.getValue());
                    }
                    if (message.size() > 0) {
                        super.consumeRow(rows);
                        consumer.acknowledge(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
