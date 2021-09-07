package com.team.domain.mongo.repository;

import com.team.domain.mongo.entity.UserEntity;
import com.team.infrastructure.base.MongoBaseRepository;

import java.util.List;

public interface InstitutionRepository extends MongoBaseRepository<UserEntity, String> {
    List<UserEntity> findByTenantId(String tenantId);
}
