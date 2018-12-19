package com.team.dao.system;

import com.team.dao.BaseDao;
import com.team.entity.system.Log;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Log 数据访问层
 * @since 2018-10-28
 */
public interface LogDao extends BaseDao<Log> {

    Integer insertByBatch(List<Log> list);

    List<Log> selectLogList();
}
