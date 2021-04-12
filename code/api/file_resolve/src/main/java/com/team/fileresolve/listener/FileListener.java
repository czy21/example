package com.team.fileresolve.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.team.domain.mongo.entity.FileColumnMappingEntity;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@Slf4j
public class FileListener extends AnalysisEventListener<Map<String, Object>> {

    public static final String FILE_LISTENER_REDIS_KEY = "data-collect-file";

    private static final int BATCH_COUNT = 3000;
    private List<Map<String, Object>> list = new ArrayList<>();
    private Map<String, Map<Integer, String>> head = new HashMap<>();
    private RedisTemplate<String, Map<String, Object>> stringListRedisTemplate;
    private FileColumnMappingRepository fileColumnMappingRepository;
    private FileColumnMappingEntity fileColumnMappingEntity;

    public FileListener(RedisTemplate<String, Map<String, Object>> stringListRedisTemplate,
                        FileColumnMappingRepository fileColumnMappingRepository) {
        this.stringListRedisTemplate = stringListRedisTemplate;
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
        head.get(context.readSheetHolder().getSheetName()).forEach((k, v) -> data.put(v, data.remove(k)));
        List<String> error_msgs = new ArrayList<>();

        for (var f : fileColumnMappingEntity.getFields()) {
            for (var v : Optional.ofNullable(f.getValidators()).orElse(new ArrayList<>())) {
                String header = f.getHeader();
                Object cellValue = data.get(header);
                boolean v_required = (boolean) v.getOrDefault("required", false);
                String v_message = (String) v.getOrDefault("message", "");
                String v_type = (String) v.get("type");
                if (v_required && ObjectUtils.isEmpty(cellValue)) {
                    error_msgs.add(v_message);
                    break;
                }
            }
        }
        data.put("error", error_msgs.size() > 0 ? StringUtils.join(",", error_msgs) : null);
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            persistent();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        persistent();
        log.info("所有数据解析完成！");
    }


    private void persistent() {
        if (CollectionUtils.isNotEmpty(list)) {
            stringListRedisTemplate.opsForList().leftPushAll(FILE_LISTENER_REDIS_KEY, list);
        }
    }
}
