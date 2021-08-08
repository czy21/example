package com.team.application.service;

import com.team.domain.entity.SaleEntity;
import com.team.domain.mongo.entity.TableMetadataEntity;

import java.util.List;

public interface PersistService {
    void persist(List<SaleEntity> sales);
}
