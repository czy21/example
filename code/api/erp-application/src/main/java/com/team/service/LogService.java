package com.team.service;

import com.team.model.SeachLogModel;

/**
 * @author 陈昭宇
 * @description Log 服务类
 * @since 2018-10-28
 */
public interface LogService {

    Object getLogPageListBy(SeachLogModel search);
}
