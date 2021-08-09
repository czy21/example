package com.team.application.service;

import com.team.application.service.impl.SaleServiceImpl;
import com.team.domain.entity.SaleEntity;

import java.util.List;

public interface PersistService {
    void persist(List<SaleEntity> maps, SaleServiceImpl.MigrateContext context);
}
