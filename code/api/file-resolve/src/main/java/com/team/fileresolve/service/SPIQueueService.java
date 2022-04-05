package com.team.fileresolve.service;

import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.List;

public interface SPIQueueService {
    void resolveFile(MaterialVO materialVO) throws Exception;

    void produceRow(String topic, RowModel rowData) throws Exception;

    void consumeRow(List<RowModel> rows);
}
