package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.team.dao.mongo.LogDao;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


        return logDao.findAll();
    }
}
