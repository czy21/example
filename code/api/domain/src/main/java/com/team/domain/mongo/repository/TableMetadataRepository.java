package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.HBaseTableMetadataEntity;
import com.team.infrastructure.entity.MongoBaseRepository;

public interface TableMetadataRepository extends MongoBaseRepository<HBaseTableMetadataEntity, String> {
    HBaseTableMetadataEntity findByNamespaceAndTableName(String namespace, String tableName);
}
