package com.team.application.config;

import com.team.domain.entity.SaleEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    @Bean
    public Job rinseJob(JobBuilderFactory jobBuilderFactory,
                        Step step11) {
        return jobBuilderFactory.get("rinseJob")
                .start(step11)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<SaleEntity> read(SqlSessionFactory sqlSessionFactory,
                                       @Value("#{jobParameters['tableName']}") String tableName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tableName", tableName);
        return new MyBatisPagingItemReaderBuilder<SaleEntity>()
                .parameterValues(parameters)
                .pageSize(100000)
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.team.domain.mapper.SaleMapper.selectAllByPage")
                .build();
    }

    @Bean
    public ItemWriter<SaleEntity> writer(SqlSessionFactory sqlSessionFactory) {
        return new MyBatisBatchItemWriterBuilder<SaleEntity>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.team.domain.mapper.SaleMapper.insert")
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Step step11(StepBuilderFactory stepBuilderFactory,
                       ItemReader<SaleEntity> reader,
                       ItemWriter<SaleEntity> writer,
                       TaskExecutor taskExecutor
    ) {
        return stepBuilderFactory
                .get("step11")
                .<SaleEntity, SaleEntity>chunk(100000)
                .reader(reader)
                .writer(writer)
                .taskExecutor(taskExecutor)
                .build();
    }


}
