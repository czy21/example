package com.team.application.service;

import com.team.domain.mongo.entity.TableMetadataEntity;

import java.util.List;
import java.util.Map;

public interface TableMetadataService {
    TableMetadataEntity findOne(String namespace, String tableName);

    void save(String namespace, String tableName, Map<String, List<String>> columnFamily);

}
