package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.team.dao.mongo.LogDao;
import com.team.entity.system.Log;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import com.team.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

/**
 * @author 陈昭宇
 * @description Log 服务实现类
 * @since 2018-10-28
 */
@Service
public class LogServiceImpl implements LogService {


    @Autowired
    private LogDao logDao;

    @Override
    public Object getLogPageListBy(SeachLogModel search) {
        return new PageUtil<>(search.getPageIndex(), search.getPageSize(), logDao.findAll());
    }
}
