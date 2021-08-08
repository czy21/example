package com.team.application.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.service.SaleService;
import com.team.application.service.TableMetadataService;
import com.team.domain.entity.SaleEntity;
import com.team.domain.mapper.SaleMapper;
import com.team.domain.mongo.entity.TableMetadataEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    HBaseService hBaseService;

    public static final String NAMESPACE = "bg_demo";

    public static final String TABLE_NAME = "sale";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TableMetadataService tableMetadataService;

    @Override
    public void migrateToHBase() {
        TableMetadataEntity tableMetadata = tableMetadataService.findOne(NAMESPACE, TABLE_NAME);
        List<SaleEntity> sales = new ArrayList<>();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            SaleMapper saleMapper = sqlSession.getMapper(SaleMapper.class);
            Cursor<SaleEntity> cursor = saleMapper.selectAllForCursor();
            for (SaleEntity t : cursor) {
                sales.add(t);
                if (sales.size() >= 100000) {
                    persist(sales, tableMetadata);
                    sales.clear();
                }
            }
            cursor.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (sales.size() > 0) {
            persist(sales, tableMetadata);
        }
    }

    @SneakyThrows
    public void persist(List<SaleEntity> sales, TableMetadataEntity metadata) {
        List<MutablePair<String, Map<String, Map<String, Object>>>> datas = new ArrayList<>();
        for (var t : sales) {
            Map<String, Object> flatValue = objectMapper.readValue(objectMapper.writeValueAsString(t), new TypeReference<>() {
            });
            Map<String, Map<String, Object>> data = metadata.getColumnFamily().entrySet().stream()
                    .map(p -> Map.<String, Map<String, Object>>of(
                            p.getKey(),
                            p.getValue().stream()
                                    .map(r -> Optional.ofNullable(flatValue.get(r)).map(o -> Map.of(r, o)).orElse(Map.of()))
                                    .collect(HashMap::new, Map::putAll, Map::putAll))
                    )
                    .collect(HashMap::new, Map::putAll, Map::putAll);
            datas.add(MutablePair.of(t.getId(), data));
        }
        LocalDateTime startTime = LocalDateTime.now();
        hBaseService.saveAll(NAMESPACE + ":" + TABLE_NAME, datas);
        LocalDateTime endTime = LocalDateTime.now();
        var timeout = Duration.between(startTime, endTime).toSeconds();
        log.info(StringUtils.join(List.of(StringUtils.join(List.of(metadata.getNamespace(), metadata.getTableName()), ":"), sales.size(), timeout), " "));
    }
}
