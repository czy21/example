package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.TableMetadataEntity;
import com.team.infrastructure.base.MongoBaseRepository;

public interface TableMetadataRepository extends MongoBaseRepository<TableMetadataEntity, String> {
    TableMetadataEntity findByNamespaceAndTableName(String namespace,String tableName);
}
