package com.team.infrastructure.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaBaseRepository<T, K> extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {
}
