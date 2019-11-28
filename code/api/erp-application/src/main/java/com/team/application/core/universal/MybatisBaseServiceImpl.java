package com.team.application.core.universal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.team.application.model.page.PageModel;
import com.team.domain.infrastructure.MybatisBaseEntity;
import com.team.domain.infrastructure.MybatisBaseMapper;
import com.team.application.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description 业务逻辑层基类实现
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
@SuppressWarnings("unchecked")
public class MybatisBaseServiceImpl<TEntity extends MybatisBaseEntity> implements MybatisBaseService<TEntity> {

    @Autowired
    protected MybatisBaseMapper<TEntity> mybatisBaseRepository;

    protected Class<TEntity> modelClass;


    protected MybatisBaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<TEntity>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void insert(TEntity entity) {
        entity.setCreatedDate(DateTimeUtil.getCurrentDateTime());
        entity.setModifiedDate(DateTimeUtil.getCurrentDateTime());
        mybatisBaseRepository.insert(entity);
    }

    @Override
    public TEntity insertAndGet(TEntity entity) {
        entity.setCreatedDate(DateTimeUtil.getCurrentDateTime());
        entity.setModifiedDate(DateTimeUtil.getCurrentDateTime());
        mybatisBaseRepository.insert(entity);
        return entity;
    }

    @Override
    public void update(TEntity entity) {
        entity.setModifiedDate(DateTimeUtil.getCurrentDateTime());
        mybatisBaseRepository.updateById(entity);
    }

    @Override
    public TEntity updateAndGet(TEntity entity) {
        entity.setModifiedDate(DateTimeUtil.getCurrentDateTime());
        mybatisBaseRepository.updateById(entity);
        return entity;
    }

    @Override
    public Integer deleteById(String id) {
        return mybatisBaseRepository.deleteById(id);
    }

    @Override
    public Integer deleteByIds(List<String> ids) {
        return mybatisBaseRepository.deleteBatchIds(ids);
    }

    @Override
    public TEntity selectOneById(String id) {
        return mybatisBaseRepository.selectById(id);
    }

    @Override
    public TEntity selectOne(SFunction<TEntity, ?> column, String value) {
        QueryWrapper<TEntity> query = new QueryWrapper<>();
        query.lambda().eq(column, value);
        return mybatisBaseRepository.selectOne(query.last("limit 1"));
    }

    @Override
    public List<TEntity> selectAll(QueryWrapper<TEntity> query) {
        query = query == null ? new QueryWrapper<>() : query;
        query.lambda().orderByDesc(TEntity::getCreatedDate);
        return mybatisBaseRepository.selectList(query);
    }

    @Override
    public PageModel<TEntity> selectAll(Integer pageIndex, Integer pageSize, QueryWrapper<TEntity> query) {
        query = query == null ? new QueryWrapper<>() : query;
        query.lambda().orderByDesc(TEntity::getCreatedDate);
        Page page = PageHelper.startPage(pageIndex, pageSize);
        mybatisBaseRepository.selectList(query);
        return PageModel.of(page);
    }

    @Override
    public PageModel<TEntity> selectAll(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        return PageModel.of(pageIndex, pageSize, list);
    }
}