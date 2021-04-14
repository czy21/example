package com.team.application.config;

import com.team.application.batch.mapper.SaleRowMapper;
import com.team.domain.entity.SaleEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ItemReader<SaleEntity> read(DataSource dataSource) {
        JdbcPagingItemReader<SaleEntity> pagingItemReader = new JdbcPagingItemReader<>();
        pagingItemReader.setDataSource(dataSource);
        pagingItemReader.setPageSize(100000);
        pagingItemReader.setRowMapper(new SaleRowMapper());
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause(List.of(
                "id",
                "from_institution_code as fromInstitutionCode",
                "from_institution_name as fromInstitutionName",
//                "from_institution_code_format as fromInstitutionCodeFormat",
                "to_institution_code as toInstitutionCode",
                "to_institution_name as toInstitutionName",
//                "to_institution_code_format as toInstitutionCodeFormat",
                "product_code as productCode",
                "product_name as productName",
                "product_spec as productSpec",
//                "product_code_format as productCodeFormat",
                "product_unit as productUnit"
//                "product_unit_format as productUnitFormat"
        ).stream().map(t -> "bf." + t).collect(Collectors.joining(",")));
        queryProvider.setFromClause("from ent_sfl_inspect_sale bf");
        pagingItemReader.setQueryProvider(queryProvider);

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);

        return pagingItemReader;
    }

    @Bean
    public ItemWriter<SaleEntity> writer() {
        return items -> {
            for (SaleEntity item : items) {
                System.out.println(item);
            }
        };
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
//                .taskExecutor(taskExecutor)
                .build();
    }


}
