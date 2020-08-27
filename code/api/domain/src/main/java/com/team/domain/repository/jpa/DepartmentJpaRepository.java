package com.team.domain.repository.jpa;

import com.team.domain.model.DepartmentEntity;
import com.team.infrastructure.base.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentJpaRepository extends JpaBaseRepository<DepartmentEntity, String> {
}
