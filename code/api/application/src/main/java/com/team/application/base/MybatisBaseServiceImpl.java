package com.team.application.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.team.application.model.page.PageModel;
import com.team.infrastructure.base.BaseEntity;
import com.team.infrastructure.base.MybatisBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MybatisBaseServiceImpl<M extends MybatisBaseMapper<T>, T extends BaseEntity> implements MybatisBaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public void insert(T entity) {
        baseMapper.insert(entity);
    }

    @Override
    public T insertAndGet(T entity) {
        baseMapper.insert(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        baseMapper.updateById(entity);
    }

    @Override
    public T updateAndGet(T entity) {
        baseMapper.updateById(entity);
        return entity;
    }

    @Override
    public Integer deleteById(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer deleteByIds(List<String> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    @Override
    public T selectOneById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public T selectOne(SFunction<T, ?> column, String value) {
        QueryWrapper<T> query = new QueryWrapper<>();
        query.lambda().eq(column, value);
        return baseMapper.selectOne(query.last("limit 1"));
    }

    @Override
    public List<T> selectAll(QueryWrapper<T> query) {
        Optional.ofNullable(query)
                .orElse(new QueryWrapper<>())
                .lambda()
                .orderByDesc(T::getCreatedDate);
        return baseMapper.selectList(query);
    }

    @Override
    public PageModel<T> selectAll(Integer pageIndex, Integer pageSize, QueryWrapper<T> query) {
        Optional.ofNullable(query)
                .orElse(new QueryWrapper<>())
                .lambda()
                .orderByDesc(T::getCreatedDate);
        Page<T> page = PageHelper.startPage(pageIndex, pageSize);
        baseMapper.selectList(query);
        return PageModel.of(page);
    }

    @Override
    public PageModel<T> selectAll(Integer pageIndex, Integer pageSize, List<T> list) {
        return PageModel.of(pageIndex, pageSize, list);
    }
}