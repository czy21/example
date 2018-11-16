package com.team.universal;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.team.exception.ServiceException;
import com.team.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Description 业务逻辑层基类实现
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
public class BaseServiceImpl<TEntity> implements BaseService<TEntity> {

    @Autowired
    private BaseDao<TEntity> baseDao;

    private Class<TEntity> modelClass;

    @SuppressWarnings("unchecked")
    protected BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<TEntity>) pt.getActualTypeArguments()[0];
    }

    @Override
    public Integer Insert(TEntity entity) {
        return baseDao.insert(entity);
    }

    @Override
    public TEntity InsertAndGetEntity(TEntity entity) {
        baseDao.insert(entity);
        return entity;
    }

    @Override
    public Integer Update(TEntity entity) {
        return baseDao.updateById(entity);
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
    public TEntity SelectBy(String fieldName, String value) {

        try {
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            QueryWrapper<TEntity> wra = new QueryWrapper<>();
            wra.eq(field.getName(), value);
            return baseDao.selectOne(wra.last("limit 1"));
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<TEntity> SelectList() {
        return baseDao.selectList(null);
    }

    @Override
    public List<TEntity> SelectListBy(Wrapper<TEntity> wrapper) {
        return baseDao.selectList(wrapper);
    }

    @Override
    public List<TEntity> SelectListBy(String fieldName, String value) {
        try {
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            QueryWrapper<TEntity> wra = new QueryWrapper<>();
            wra.eq(field.getName(), value);
            wra.orderByAsc("ModifiedTime");
            return baseDao.selectList(wra);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public PageModel<TEntity> SelectPageList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        QueryWrapper<TEntity> wra = new QueryWrapper<>();
        wra.orderByAsc("ModifiedTime");
        return new PageModel<>(baseDao.selectList(wra));
    }
}