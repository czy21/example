package com.team.application.config;

import com.team.domain.entity.SaleEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
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
        pagingItemReader.setQueryId("com.team.domain.mapper.SaleMapper.selectByPage");
        return pagingItemReader;
    }

    @Bean
    public ItemWriter<SaleEntity> writer(DataSource dataSource) {
        JdbcBatchItemWriter<SaleEntity> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql(
                "insert into ent_sfl_inspect_sale_1"
                        + "(id,from_institution_code,from_institution_name,to_institution_code,to_institution_name,product_code,product_name,product_spec,product_unit) values"
                        + "(:id,:fromInstitutionCode,:fromInstitutionName,:toInstitutionCode,:toInstitutionName,:productCode,:productName,:productSpec,:productUnit) ");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

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
