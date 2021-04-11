package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.FileColumnMappingEntity;
import com.team.infrastructure.base.MongoBaseRepository;

public interface FileColumnMappingRepository extends MongoBaseRepository<FileColumnMappingEntity, String> {
    FileColumnMappingEntity findByBusinessTypeEquals(String businessType);
}
