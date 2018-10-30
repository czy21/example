package com.czy.core.universal;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @Description 业务逻辑层基类
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
public interface BaseService<TEntity> {

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
     * @Description 修改实体
     * @Date 2018/7/26
     * @Param [entity]
     * @Return java.lang.Integer
     */
    Integer Update(TEntity entity);

    /*
     * @Author 陈昭宇
     * @Description 根据Id删除实体
     * @Date 2018/7/26
     * @Param [id]
     * @Return java.lang.Integer
     */
    Integer DeleteById(String id);

    /*
     * @Author 陈昭宇
     * @Description 根据Id集合,批量删除实体
     * @Date 2018/7/26
     * @Param [ids]
     * @Return java.lang.Integer
     */
    Integer DeleteByIds(List<String> ids);

    /*
     * @Author 陈昭宇
     * @Description 根据Id获取实体
     * @Date 2018/7/26
     * @Param [id]
     * @Return TEntity
     */
    TEntity SelectById(String id);

    /*
     * @Author 陈昭宇
     * @Description 根据属性值获取实体
     * @Date 2018/7/26
     * @Param [fieldName, value]
     * @Return TEntity
     */
    TEntity SelectBy(String fieldName, String value) throws TooManyResultsException;

    /*
     * @Author 陈昭宇
     * @Description 获取实体集合
     * @Date 2018/7/26
     * @Param []
     * @Return java.util.List<TEntity>
     */
    List<TEntity> SelectList();

    /*
     * @Author 陈昭宇
     * @Description 根据条件获取实体集合
     * @Date 2018/7/26
     * @Param [wrapper]
     * @Return java.util.List<TEntity>
     */
    List<TEntity> SelectListBy(Wrapper<TEntity> wrapper);

    /*
     * @Author 陈昭宇
     * @Description 根据字段获取实体集合
     * @Date 2018/7/26
     * @Param [fieldName, value]
     * @Return java.util.List<TEntity>
     */
    List<TEntity> SelectListBy(String fieldName, String value);

    /*
     * @Author 陈昭宇
     * @Description 分页查询
     * @Date 2018/7/26
     * @Param [pageIndex, pageSize]
     * @Return PageModel<TEntity>
     */
    PageModel<TEntity> SelectPageList(Integer pageIndex, Integer pageSize);

}
