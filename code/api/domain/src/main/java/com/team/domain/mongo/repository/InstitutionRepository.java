package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.UserEntity;
import com.team.infrastructure.entity.MongoBaseRepository;

import java.util.List;

public interface InstitutionRepository extends MongoBaseRepository<UserEntity, String> {
    List<UserEntity> findByTenantId(String tenantId);
}
