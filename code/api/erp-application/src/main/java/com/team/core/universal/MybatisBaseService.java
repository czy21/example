package com.team.core.universal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.team.entity.page.PageModel;
import com.team.util.PageUtil;
import com.team.entity.BaseEntity;

import java.util.List;

/**
 * @Description 业务逻辑层基类
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
public interface MybatisBaseService<TEntity extends BaseEntity> {

    /*
     * @Author 陈昭宇
     * @Description 添加实体
     * @Date 2018/7/26
     * @Param [entity]
     * @Return java.lang.Integer
     */
    Integer Insert(TEntity entity);

    /*
     * @Author 陈昭宇
     * @Description 添加并返回实体
     * @Date 2018/7/26
     * @Param [entity]
     * @Return TEntity
     */
    TEntity InsertAndGetEntity(TEntity entity);

    /*
     * @Author 陈昭宇
     * @Description 修改实体
     * @Date 2018/7/26
     * @Param [entity]
     * @Return java.lang.Integer
     */
    Integer Update(TEntity entity);

    /*
     * @Author 陈昭宇
     * @Description 修改实体
     * @Date 2018/7/26
     * @Param [entity]
     * @Return TEntity
     */
    TEntity UpdateAndGetEntity(TEntity entity);

    /*
     * @Author 陈昭宇
     * @Description 修改实体
     * @Date 2018/7/26
     * @Param [entity,wrapper]
     * @Return java.lang.Integer
     */
    Integer UpdateBy(TEntity entity, UpdateWrapper<TEntity> query);


    /*
     * @Author 陈昭宇
     * @Description 根据Id删除实体
     * @Date 2018/7/26
     * @Param [id]
     * @Return java.lang.Integer
     */
    Integer DeleteById(Long id);

    /*
     * @Author 陈昭宇
     * @Description 根据Id集合,批量删除实体
     * @Date 2018/7/26
     * @Param [ids]
     * @Return java.lang.Integer
     */
    Integer DeleteByIds(List<Long> ids);

    /*
     * @Author 陈昭宇
     * @Description 根据Id获取实体
     * @Date 2018/7/26
     * @Param [id]
     * @Return TEntity
     */
    TEntity SelectById(Long id);

    /*
     * @Author 陈昭宇
     * @Description 根据字段值获取实体
     * @Date 2018/7/26
     * @Param [fieldName, value]
     * @Return TEntity
     */
    TEntity SelectBy(SFunction<TEntity, ?> column, String value);

    /*
     * @Author 陈昭宇
     * @Description 根据条件获取实体集合
     * @Date 2018/7/26
     * @Param [wrapper]
     * @Return java.util.List<TEntity>
     */
    List<TEntity> SelectListBy(QueryWrapper<TEntity> query);

    /*
     * @Author 陈昭宇
     * @Description 根据条件分页查询(mybatis分页)
     * @Date 2018/7/26
     * @Param [pageIndex, pageSize,query]
     * @Return PageModel<TEntity>
     */
    PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, QueryWrapper<TEntity> query);


    /*
     * @Author 陈昭宇
     * @Description 根据条件分页查询(list物理分页)
     * @Date 2018/7/26
     * @Param [pageIndex, pageSize,query]
     * @Return PageModel<TEntity>
     */
    PageUtil<TEntity> SelectPageList(Integer pageIndex, Integer pageSize, List<TEntity> list);

}
