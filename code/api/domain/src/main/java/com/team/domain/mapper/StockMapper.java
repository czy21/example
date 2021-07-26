package com.team.domain.mapper;


import com.team.domain.entity.StockEntity;
import com.team.infrastructure.base.MybatisBaseMapper;

public interface StockMapper extends MybatisBaseMapper<StockEntity> {

    void reduce(StockEntity userPO);
}
