package com.team.application.service.impl;

import com.team.application.service.TableMetadataService;
import com.team.domain.mongo.entity.HBaseTableMetadataEntity;
import com.team.domain.mongo.repository.TableMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TableMetadataServiceImpl implements TableMetadataService {

    @Autowired
    TableMetadataRepository tableMetadataRepository;

    @Override
    public HBaseTableMetadataEntity findOne(String namespace, String tableName) {
        return tableMetadataRepository.findByNamespaceAndTableName(namespace, tableName);
    }

    @Override
    public void save(String namespace, String tableName, Map<String, List<String>> columnFamily) {
        HBaseTableMetadataEntity entity = new HBaseTableMetadataEntity();
        entity.setNamespace(namespace);
        entity.setTableName(tableName);
        entity.setColumnFamily(columnFamily);
        tableMetadataRepository.save(entity);
    }
}
