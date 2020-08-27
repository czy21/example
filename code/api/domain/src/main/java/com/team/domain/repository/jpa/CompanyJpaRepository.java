package com.team.domain.repository.jpa;

import com.team.domain.model.CompanyEntity;
import com.team.infrastructure.base.JpaBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJpaRepository extends JpaBaseRepository<CompanyEntity, String> {
}
