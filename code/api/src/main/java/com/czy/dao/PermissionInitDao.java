package com.czy.dao;

import com.czy.entity.po.PermissionInit;
import com.czy.core.universal.BaseDao;

import java.util.List;

/**
 * @author 陈昭宇
 * @description PermissionInit 数据访问层
 * @since 2018-10-20
 */
public interface PermissionInitDao extends BaseDao<PermissionInit> {

    List<PermissionInit> selectAllOrderBySort();

}
