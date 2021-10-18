package com.team.fileresolve.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.team.application.config.QueueConfig;
import com.team.domain.mongo.entity.FileColumnMappingEntity;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class FileListener extends AnalysisEventListener<Map<Integer, Object>> {

    Map<String, Map<String, FileColumnMappingEntity.Field>> columnMetadata = new ConcurrentHashMap<>();
    RabbitTemplate rabbitTemplate;
    FileColumnMappingRepository fileColumnMappingRepository;

    public FileListener(RabbitTemplate rabbitTemplate, FileColumnMappingRepository fileColumnMappingRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.fileColumnMappingRepository = fileColumnMappingRepository;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        String currentSheet = context.readSheetHolder().getSheetName();
        var fe = fileColumnMappingRepository.findByBusinessTypeEquals(currentSheet);
        columnMetadata.put(currentSheet, fe.getFields().stream().collect(Collectors.toMap(FileColumnMappingEntity.Field::getKey, t -> {
            t.setBusinessType(currentSheet);
            t.setTableName(fe.getTableName());
            headMap.entrySet().stream().filter(p -> p.getValue().equals(t.getHeader()))
                    .findFirst()
                    .ifPresent(h -> t.setIndex(h.getKey()));
            return t;
        })));
    }

    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext context) {
        String businessType = context.readSheetHolder().getSheetName();
        String sqlCommand = "INSERT";

        var msg = columnMetadata.get(businessType).entrySet().stream()
                .map(t -> {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put("businessType", businessType);
                    obj.put("sqlCommand", sqlCommand);
                    obj.put("tableName", t.getValue().getTableName());
                    Integer colIndex = Optional.ofNullable(t.getValue().getIndex()).orElse(null);
                    if (colIndex != null) {
                        Object cellValue = data.get(colIndex);
                        obj.put(t.getKey(), cellValue);
                    }
                    return obj;
                }).collect(HashMap::new, Map::putAll, Map::putAll);
        rabbitTemplate.convertAndSend(QueueConfig.SPI_DATA_TOPIC, msg);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }

}
