package com.team.service.impl;

import com.team.entity.system.Log;
import com.team.service.LogService;
import com.team.system.LogDao;
import com.team.universal.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈昭宇
 * @description Log 服务实现类
 * @since 2018-10-28
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public Integer insertByBatch(List<Log> list) {
        return logDao.insertByBatch(list);
    }
}
