package com.team.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.application.model.dto.StockDTO;
import com.team.domain.entity.StockEntity;

public interface StockService extends IService<StockEntity> {
    void add(StockDTO dto);

    void reduce(StockDTO dto);
}
