package com.team.service.impl;

import com.team.dao.mongo.LogDao;
import com.team.entity.page.PageModel;
import com.team.entity.system.Log;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Log> list = logDao.findAll(PageRequest.of(search.getPageIndex() - 1, search.getPageSize())).getContent();
        return new PageModel<Log>(search.getPageIndex(), search.getPageSize(), list);


    }
}
