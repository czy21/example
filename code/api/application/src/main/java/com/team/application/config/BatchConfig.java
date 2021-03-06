package com.team.application.config;

import org.apache.ibatis.session.SqlSessionFactory;
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
    public ItemReader<Map<String, Object>> read(SqlSessionFactory sqlSessionFactory,
                                                @Value("#{jobParameters['tableName']}") String tableName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("tableName", tableName);
        return new MyBatisPagingItemReaderBuilder<Map<String, Object>>()
                .parameterValues(parameters)
                .pageSize(100000)
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.team.domain.mapper.SaleMapper.selectAllByPage")
                .build();
    }

    @Bean
    public ItemWriter<Map<String, Object>> writer(SqlSessionFactory sqlSessionFactory) {
        return items -> items.forEach(t -> {
            t.put("sqlCommand", "UPDATE");
//            kafkaTemplate.send(QueueConfig.SPI_DATA_TOPIC, t);
        });
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Step step11(StepBuilderFactory stepBuilderFactory,
                       ItemReader<Map<String, Object>> reader,
                       ItemWriter<Map<String, Object>> writer,
                       TaskExecutor taskExecutor
    ) {
        return stepBuilderFactory
                .get("step11")
                .<Map<String, Object>, Map<String, Object>>chunk(100000)
                .reader(reader)
                .writer(writer)
                .taskExecutor(taskExecutor)
                .build();
    }


}
