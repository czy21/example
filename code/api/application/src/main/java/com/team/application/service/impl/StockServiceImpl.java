package com.team.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.application.model.dto.StockDTO;
import com.team.application.service.StockService;
import com.team.domain.entity.StockEntity;
import com.team.domain.entity.StockLogEntity;
import com.team.domain.mapper.StockLogMapper;
import com.team.domain.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, StockEntity> implements StockService {

    @Autowired
    StockMapper stockMapper;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    StockLogMapper stockLogMapper;

    @Override
    public void add(StockDTO dto) {
        StockEntity po = new StockEntity();
        po.setName(dto.getName());
        QueryWrapper<StockEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StockEntity::getName, dto.getName());
        if (stockMapper.selectOne(queryWrapper) != null) {
            throw new RuntimeException(StringUtils.join(List.of(dto.getName(), "已存在"), " "));
        }
        po.setId(UUID.randomUUID().toString());
        po.setCount(dto.getCount());
        stockMapper.insert(po);
    }

    @Override
    public void reduce(StockDTO dto) {
        StockEntity po = new StockEntity();
        po.setId(dto.getId());
        String lockKey = StringUtils.join(List.of("lock", dto.getId()), ":");
        log.info(StringUtils.join(List.of(dto.getUserId(), "enter", dto.getSubmitDate()), " "));
        RLock lock = redissonClient.getFairLock(lockKey);
        try {
            lock.lock();
            StockEntity stock = stockMapper.selectById(po.getId());
            if (stock == null) {
                throw new RuntimeException(StringUtils.join(List.of("该商品不存在"), " "));
            }
            if (stock.getCount() == 0) {
                throw new RuntimeException(StringUtils.join(List.of(stock.getName(), "库存不足"), " "));
            }
            StockLogEntity sl = new StockLogEntity();
            sl.setStockId(po.getId());
            sl.setUserId(dto.getUserId());
            sl.setCreateTime(dto.getSubmitDate());
            sl.setUpdateTime(LocalDateTime.now());
            stockLogMapper.insert(sl);
            stockMapper.reduce(po);
        } finally {
            lock.unlock();
        }

    }
}

