package com.team.core.universal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.team.infrastructure.MybatisBaseEntity;
import com.team.entity.page.PageModel;
import com.team.infrastructure.MybatisBaseMapper;
import com.team.util.DateTimeUtil;
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
    public Integer Insert(TEntity entity) {
        entity.setAddedTime(DateTimeUtil.getCurrentDateTime());
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        return mybatisBaseRepository.insert(entity);
    }

    @Override
    public TEntity InsertAndGetEntity(TEntity entity) {
        entity.setAddedTime(DateTimeUtil.getCurrentDateTime());
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        mybatisBaseRepository.insert(entity);
        return entity;
    }

    @Override
    public Integer Update(TEntity entity) {
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        return mybatisBaseRepository.updateById(entity);
    }

    @Override
    public TEntity UpdateAndGetEntity(TEntity entity) {
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        mybatisBaseRepository.updateById(entity);
        return entity;
    }

    @Override
    public Integer UpdateBy(TEntity entity, UpdateWrapper<TEntity> query) {
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        return mybatisBaseRepository.update(entity, query);
    }

    @Override
    public Integer DeleteById(Long id) {
        return mybatisBaseRepository.deleteById(id);
    }

    @Override
    public Integer DeleteByIds(List<Long> ids) {
        return mybatisBaseRepository.deleteBatchIds(ids);
    }

    @Override
    public TEntity SelectById(Long id) {
        return mybatisBaseRepository.selectById(id);
    }

    @Override
    public TEntity SelectBy(SFunction<TEntity, ?> column, String value) {
        QueryWrapper<TEntity> query = new QueryWrapper<>();
        query.lambda().eq(column, value);
        return mybatisBaseRepository.selectOne(query.last("limit 1"));
    }

    @Override
    public List<TEntity> SelectListBy(QueryWrapper<TEntity> query) {
        query = query == null ? new QueryWrapper<>() : query;
        query.lambda().orderByDesc(TEntity::getModifiedTime);
        return mybatisBaseRepository.selectList(query);
    }

    @Override
    public PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, QueryWrapper<TEntity> query) {
        query = query == null ? new QueryWrapper<>() : query;
        query.lambda().orderByDesc(TEntity::getModifiedTime);
        Page page = PageHelper.startPage(pageIndex, pageSize);
        mybatisBaseRepository.selectList(query);
        return PageModel.of(page);
    }

    @Override
    public PageModel<TEntity> SelectPageList(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        return PageModel.of(pageIndex, pageSize, list);
    }
}