package com.team.fileresolve.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.team.application.automap.RowAutoMap;
import com.team.application.config.QueueConfig;
import com.team.application.kind.SqlKind;
import com.team.application.model.RowModel;
import com.team.application.model.vo.MaterialVO;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.mongo.entity.FileColumnMappingEntity;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import com.team.fileresolve.service.SPIQueueService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class FileListener extends AnalysisEventListener<Map<Integer, Object>> {

    SPIQueueService SPIQueueService;
    FileColumnMappingRepository fileColumnMappingRepository;
    RowAutoMap rowAutoMap;
    MaterialEntity materialEntity;
    MaterialVO materialVO;
    Map<String, MutablePair<FileColumnMappingEntity, Map<String, FileColumnMappingEntity.Field>>> columnMetadata = new ConcurrentHashMap<>();

    public FileListener(SPIQueueService SPIQueueService,
                        FileColumnMappingRepository fileColumnMappingRepository,
                        RowAutoMap rowAutoMap,
                        MaterialEntity materialEntity,
                        MaterialVO materialVO) {
        this.SPIQueueService = SPIQueueService;
        this.fileColumnMappingRepository = fileColumnMappingRepository;
        this.rowAutoMap = rowAutoMap;
        this.materialEntity = materialEntity;
        this.materialVO = materialVO;

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        String currentSheet = context.readSheetHolder().getSheetName();
        var fe = fileColumnMappingRepository.findByBusinessTypeEquals(currentSheet);
        columnMetadata.put(currentSheet, MutablePair.of(fe,
                fe.getFields().stream()
                        .collect(Collectors.toMap(FileColumnMappingEntity.Field::getKey,
                                t -> {
                                    headMap.entrySet().stream()
                                            .filter(p -> p.getValue().equals(t.getHeader()))
                                            .findFirst()
                                            .ifPresent(h -> t.setIndex(h.getKey()));
                                    return t;
                                }))));
    }

    @SneakyThrows
    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext context) {
        String businessType = context.readSheetHolder().getSheetName();
        Integer rowIndex = context.readRowHolder().getRowIndex();
        var fileTableMeta = columnMetadata.get(businessType).getKey();
        var fileFieldMeta = columnMetadata.get(businessType).getValue();

        RowModel rowModel = new RowModel();
        rowModel.setTableName(fileTableMeta.getTableName());
        rowModel.setBusinessType(fileTableMeta.getBusinessType());
        rowModel.setSqlKind(SqlKind.INSERT);
        rowModel.setFileId(materialEntity.getId());
        rowModel.setIndex(rowIndex);

        Map<String, RowModel.ColModel> rowData = fileFieldMeta.values().stream()
                .map(f -> {
                    RowModel.ColModel col = rowAutoMap.mapToTarget(f);
                    Integer colIndex = f.getIndex();
                    if (colIndex != null) {
                        Object cellValue = data.get(colIndex);
                        col.setValue(cellValue);
                    }
                    return col;
                }).collect(LinkedHashMap::new, (m, t) -> m.put(t.getKey(), t), Map::putAll);
        rowModel.setData(rowData);
        SPIQueueService.produceRow(QueueConfig.SPI_DATA_TOPIC, rowModel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }

}
