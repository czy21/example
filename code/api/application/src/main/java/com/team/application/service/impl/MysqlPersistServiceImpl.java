package com.team.application.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.team.application.annotation.ProcessMonitor;
import com.team.application.service.PersistService;
import com.team.domain.entity.SaleEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class MysqlPersistServiceImpl implements PersistService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
    }

    public static String[] SALE_COLUMNS = new String[]{
            "id",
            "sale_date",
            "access_type",
            "from_institution_code",
            "from_institution_name",
            "from_institution_code_format",
            "to_institution_code",
            "to_institution_name",
            "to_institution_code_format",
            "product_code", "product_name",
            "product_spec",
            "product_batch",
            "product_quantity",
            "product_unit",
            "product_price",
            "product_amount",
            "producer",
            "product_code_format",
            "product_unit_format",
            "product_quantity_format"
    };

    public static final String TABLE_NAME = "ent_sale_1";

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(0) from " + TABLE_NAME, Integer.class);
    }

    @ProcessMonitor
    @SneakyThrows
    @Override
    public void persist(List<SaleEntity> sales, SaleServiceImpl.MigrateContext context) {
        List<String> columns = Arrays.stream(SALE_COLUMNS).collect(Collectors.toCollection(LinkedList::new));
        String values = IntStream.range(0, columns.size()).mapToObj(t -> "?").collect(Collectors.joining(","));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        List<Map<String, Object>> maps = objectMapper.readValue(objectMapper.writeValueAsString(sales), new TypeReference<>() {
        });
        jdbcTemplate.batchUpdate("insert into " + TABLE_NAME + "(" + String.join(",", columns) + ")" + "values(" + values + ")",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Map<String, Object> t = maps.get(i);
                        for (int j = 0; j < columns.size(); j++) {
                            String c = columns.get(j);
                            Object value = t.get(c);
                            ps.setObject(j + 1, c.equals("id") ? UUID.randomUUID().toString().replace("-", "") : value);
                        }
                    }

                    @Override
                    public int getBatchSize() {
                        return maps.size();
                    }
                });
    }
}
