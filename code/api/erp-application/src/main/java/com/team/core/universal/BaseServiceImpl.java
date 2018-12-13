package com.team.core.universal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.pagehelper.PageHelper;
import com.team.entity.page.PageModel;
import com.team.util.DateTimeUtil;
import com.team.util.PageUtil;
import com.team.dal.BaseDao;
import com.team.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description 业务逻辑层基类实现
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
public class BaseServiceImpl<TEntity extends BaseEntity> implements BaseService<TEntity> {

    @Autowired
    protected BaseDao<TEntity> baseDao;

    protected Class<TEntity> modelClass;

    @SuppressWarnings("unchecked")
    protected BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<TEntity>) pt.getActualTypeArguments()[0];
    }

    @Override
    public Integer Insert(TEntity entity) {
        entity.setAddedTime(DateTimeUtil.getCurrentDateTime());
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        return baseDao.insert(entity);
    }

    @Override
    public TEntity InsertAndGetEntity(TEntity entity) {
        entity.setAddedTime(DateTimeUtil.getCurrentDateTime());
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        baseDao.insert(entity);
        return entity;
    }

    @Override
    public Integer Update(TEntity entity) {
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        return baseDao.updateById(entity);
    }

    @Override
    public TEntity UpdateAndGetEntity(TEntity entity) {
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        baseDao.updateById(entity);
        return entity;
    }

    @Override
    public Integer UpdateBy(TEntity entity, UpdateWrapper<TEntity> query) {
        entity.setModifiedTime(DateTimeUtil.getCurrentDateTime());
        return baseDao.update(entity, query);
    }

    @Override
    public Integer DeleteById(String id) {
        return baseDao.deleteById(id);
    }

    @Override
    public Integer DeleteByIds(List<String> ids) {
        return baseDao.deleteBatchIds(ids);
    }

    @Override
    public TEntity SelectById(String id) {
        return baseDao.selectById(id);
    }

    @Override
    public TEntity SelectBy(SFunction<TEntity, ?> column, String value) {
        QueryWrapper<TEntity> query = new QueryWrapper<>();
        query.lambda().eq(column, value);
        return baseDao.selectOne(query.last("limit 1"));
    }

    @Override
    public List<TEntity> SelectListBy(QueryWrapper<TEntity> query) {
        if (query == null) {
            query = new QueryWrapper<>();
        }
        query.lambda().orderByDesc(TEntity::getModifiedTime);
        return baseDao.selectList(query);
    }

    @Override
    public PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, QueryWrapper<TEntity> query) {
        if (query == null) {
            query = new QueryWrapper<>();
        }
        query.lambda().orderByDesc(TEntity::getModifiedTime);
        PageHelper.startPage(pageIndex, pageSize);
        return new PageModel<>(baseDao.selectList(query));
    }

    @Override
    public PageUtil<TEntity> SelectPageList(Integer pageIndex, Integer pageSize, List<TEntity> list) {
        return new PageUtil<>(pageIndex, pageSize, list);
    }
}