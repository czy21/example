package com.team.domain.mapper;


import com.team.domain.entity.StockEntity;
import com.team.infrastructure.entity.MybatisBaseMapper;

public interface StockMapper extends MybatisBaseMapper<StockEntity> {

    void reduce(StockEntity userPO);
}
