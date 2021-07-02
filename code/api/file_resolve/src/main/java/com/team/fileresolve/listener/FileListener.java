package com.team.fileresolve.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.team.application.ApplicationConfig;
import com.team.domain.mongo.entity.FileColumnMappingEntity;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileListener extends AnalysisEventListener<Map<String, Object>> {

    Map<String, Map<Integer, String>> head = new HashMap<>();
    KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    FileColumnMappingRepository fileColumnMappingRepository;
    FileColumnMappingEntity fileColumnMappingEntity;

    public FileListener(KafkaTemplate<String, Map<String, Object>> kafkaTemplate,
                        FileColumnMappingRepository fileColumnMappingRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.fileColumnMappingRepository = fileColumnMappingRepository;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        String currentSheet = context.readSheetHolder().getSheetName();
        head.put(context.readSheetHolder().getSheetName(), headMap);
        fileColumnMappingEntity = fileColumnMappingRepository.findByBusinessTypeEquals(currentSheet);
    }

    @Override
    public void invoke(Map<String, Object> data, AnalysisContext context) {
        data.put("sqlCommand", "INSERT");
        data.put("businessType", context.readSheetHolder().getSheetName());
        head.get(context.readSheetHolder().getSheetName()).forEach((k, v) -> data.put(v, data.remove(k)));
        kafkaTemplate.send(ApplicationConfig.SPI_DATA_TOPIC, data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }

}
