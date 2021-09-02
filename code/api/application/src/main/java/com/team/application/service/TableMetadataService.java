package com.team.application.service;

import com.team.domain.mongo.entity.HBaseTableMetadataEntity;

import java.util.List;
import java.util.Map;

public interface TableMetadataService {
    HBaseTableMetadataEntity findOne(String namespace, String tableName);

    void save(String namespace, String tableName, Map<String, List<String>> columnFamily);

}
