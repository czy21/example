package com.team.application.file;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericDataEventListener<T> extends AnalysisEventListener<T> {

    private final Logger logger = LoggerFactory.getLogger(GenericDataEventListener.class);

    private int batchSize = 2000;
    private final List<Row<T>> rows = new ArrayList<>();
    private final Map<Integer, List<String>> errors = new TreeMap<>();
    private Consumer<Context<T>> processConsumer;
    private StringRedisTemplate redisTemplate;
    private String token;
    private final ObjectMapper objectMapper;

    private int expireMinutes = 30;

    public Function<String, String> dataKeyPrefix = t -> t + ":" + "data";
    public Function<String, String> errorKeyPrefix = t -> t + ":" + "error";

    public GenericDataEventListener(Consumer<Context<T>> processConsumer, ObjectMapper objectMapper) {
        this.processConsumer = processConsumer;
        this.objectMapper = objectMapper;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public Consumer<Context<T>> getProcessConsumer() {
        return processConsumer;
    }

    public void setProcessConsumer(Consumer<Context<T>> processConsumer) {
        this.processConsumer = processConsumer;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        int rowIndex = analysisContext.readRowHolder().getRowIndex() + 1;
        errors.put(rowIndex, new ArrayList<>());
        rows.add(new Row<>(t, rowIndex));
        if (rows.size() >= batchSize) {
            processRows();
            rows.clear();
            errors.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        processRows();
    }

    private void processRows() {
        processConsumer.accept(new Context<>(rows, Collections.unmodifiableMap(errors)));
        String dataKey = dataKeyPrefix.apply(token);
        for (Row<T> t : rows) {
            try {
                redisTemplate.opsForHash().put(dataKey, String.valueOf(t.getRowIndex()), objectMapper.writeValueAsString(t.getData()));
            } catch (JsonProcessingException e) {
                logger.error("excel存储数据失败", e);
            }
        }
        redisTemplate.expire(dataKey, expireMinutes, TimeUnit.MINUTES);
        String errorKey = errorKeyPrefix.apply(token);
        for (Map.Entry<Integer, List<String>> m : errors.entrySet().stream().filter(t -> CollectionUtils.isNotEmpty(t.getValue())).toList()) {
            try {
                redisTemplate.opsForHash().put(errorKey, String.valueOf(m.getKey()), objectMapper.writeValueAsString(m.getValue()));
            } catch (JsonProcessingException e) {
                logger.error("excel 存储错误信息失败", e);
            }
        }
        redisTemplate.expire(errorKey, expireMinutes, TimeUnit.MINUTES);
    }

    /**
     * 数据行
     *
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    public static class Row<T> {
        private T data;
        private int rowIndex;
    }

    /**
     * 数据处理上下文
     *
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    public static class Context<T> {
        private List<Row<T>> rows;
        private Map<Integer, List<String>> errors;
    }
}
