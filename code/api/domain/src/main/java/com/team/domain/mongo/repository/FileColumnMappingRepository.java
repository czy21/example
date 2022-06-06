package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.FileColumnMappingEntity;
import com.team.infrastructure.entity.MongoBaseRepository;

public interface FileColumnMappingRepository extends MongoBaseRepository<FileColumnMappingEntity, String> {
    FileColumnMappingEntity findByBusinessTypeEquals(String businessType);
}
