package com.czy.dao;

import com.czy.entity.po.Log;
import com.czy.core.universal.BaseDao;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogDao extends BaseDao<Log> {

    Integer insertByBatch(List<Log> list);
}
