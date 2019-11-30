package com.team.application.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.team.application.model.page.PageModel;
import com.team.infrastructure.base.MybatisBaseEntity;

import java.util.List;

public interface MybatisBaseService<T extends MybatisBaseEntity> {

    void insert(T entity);

    T insertAndGet(T entity);

    void update(T entity);

    T updateAndGet(T entity);

    Integer deleteById(String id);

    Integer deleteByIds(List<String> ids);

    T selectOneById(String id);

    T selectOne(SFunction<T, ?> column, String value);

    List<T> selectAll(QueryWrapper<T> query);

    PageModel<T> selectAll(Integer pageIndex, Integer pageSize, QueryWrapper<T> query);

    PageModel<T> selectAll(Integer pageIndex, Integer pageSize, List<T> list);

}
