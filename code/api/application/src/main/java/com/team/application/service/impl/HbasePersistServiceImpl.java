package com.team.application.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.annotation.ProcessMonitor;
import com.team.application.service.PersistService;
import com.team.application.service.TableMetadataService;
import com.team.domain.entity.SaleEntity;
import com.team.domain.mongo.entity.TableMetadataEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class HbasePersistServiceImpl implements PersistService {
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
    public int count() {
        return hBaseService.count(NAMESPACE + ":" + TABLE_NAME);
    }

    @ProcessMonitor
    @SneakyThrows
    @Override
    public void persist(List<SaleEntity> maps, SaleServiceImpl.MigrateContext context) {
        TableMetadataEntity metadata = tableMetadataService.findOne(NAMESPACE, TABLE_NAME);
        List<MutablePair<String, Map<String, Map<String, Object>>>> datas = new ArrayList<>();
        for (var t : maps) {
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
        hBaseService.saveAll(NAMESPACE + ":" + TABLE_NAME, datas);
    }
}
