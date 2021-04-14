package com.team.application.config;

import com.team.domain.entity.SaleEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.HashMap;

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
    public ItemReader<SaleEntity> read(SqlSessionFactory sqlSessionFactory) {
        MyBatisPagingItemReader<SaleEntity> pagingItemReader = new MyBatisPagingItemReader<>();
        pagingItemReader.setParameterValues(new HashMap<>());
        pagingItemReader.setPageSize(100000);
        pagingItemReader.setSqlSessionFactory(sqlSessionFactory);
        pagingItemReader.setQueryId("com.team.domain.mapper.SaleMapper.selectAllByPage");
        return pagingItemReader;
    }

    @Bean
    public ItemWriter<SaleEntity> writer(SqlSessionFactory sqlSessionFactory) {
        MyBatisBatchItemWriter<SaleEntity> writer = new MyBatisBatchItemWriter<>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("com.team.domain.mapper.SaleMapper.insert");
        return writer;
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
