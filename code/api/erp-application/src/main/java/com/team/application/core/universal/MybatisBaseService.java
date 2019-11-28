package com.team.application.core.universal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.team.application.model.page.PageModel;
import com.team.domain.infrastructure.base.MybatisBaseEntity;

import java.util.List;

public interface MybatisBaseService<TEntity extends MybatisBaseEntity> {

    void insert(TEntity entity);

    TEntity insertAndGet(TEntity entity);

    void update(TEntity entity);

    TEntity updateAndGet(TEntity entity);

    Integer deleteById(String id);

    Integer deleteByIds(List<String> ids);

    TEntity selectOneById(String id);

    TEntity selectOne(SFunction<TEntity, ?> column, String value);

    List<TEntity> selectAll(QueryWrapper<TEntity> query);

    PageModel<TEntity> selectAll(Integer pageIndex, Integer pageSize, QueryWrapper<TEntity> query);

    PageModel<TEntity> selectAll(Integer pageIndex, Integer pageSize, List<TEntity> list);

}
