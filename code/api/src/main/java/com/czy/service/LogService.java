package com.czy.service;

import com.czy.entity.po.Log;
import com.czy.core.universal.BaseService;

import java.util.List;

/**
 * @description Log 服务类
 * @author 陈昭宇
 * @since 2018-10-28
 */
public interface LogService extends BaseService<Log> {

    Integer insertByBatch(List<Log> list);
}
