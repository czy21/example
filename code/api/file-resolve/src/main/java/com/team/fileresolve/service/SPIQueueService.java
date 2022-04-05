package com.team.fileresolve.service;

import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;

import java.util.List;

public interface SPIQueueService {
    void resolveFile(MaterialVO materialVO) throws Exception;

    void produceRow(String topic, RowModel rowData);

    void consumeRow(List<RowModel> rows);
}
