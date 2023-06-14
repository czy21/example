package com.team.application.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.InputStream;
import java.util.UUID;
import java.util.function.Consumer;

public class EasyExcelReadService<T> {

    private InputStream inputStream;
    /**
     * 本次文件导入的唯一id
     */
    private String token;
    private int batchSize = 2000;
    private GenericDataEventListener<T> genericDataEventListener;
    public EasyExcelReadService<T> file(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public EasyExcelReadService<T> batchSize(int batchSize) {
        if (batchSize > 0) {
            this.batchSize = batchSize;
        }
        return this;
    }

    public String getToken() {
        return token;
    }

    public EasyExcelReadService<T> process(Consumer<GenericDataEventListener.Context<T>> consumer,
                                           ObjectMapper objectMapper,
                                           StringRedisTemplate redisTemplate) {
        this.token = UUID.randomUUID().toString().replace("-", "");
        this.genericDataEventListener = new GenericDataEventListener<>(consumer, objectMapper);
        this.genericDataEventListener.setToken(token);
        this.genericDataEventListener.setBatchSize(batchSize);
        this.genericDataEventListener.setRedisTemplate(redisTemplate);
        return this;
    }

    public ExcelReaderBuilder head(Class<T> clazz) {
        return EasyExcel.read(inputStream, clazz, genericDataEventListener);
    }
}
