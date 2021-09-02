package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.InstitutionEntity;
import com.team.infrastructure.base.MongoBaseRepository;

import java.util.List;

public interface InstitutionRepository extends MongoBaseRepository<InstitutionEntity, String> {
    List<InstitutionEntity> findByTenantId(String tenantId);
}
