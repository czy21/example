package com.team.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.team.core.universal.BaseServiceImpl;
import com.team.dao.system.LogDao;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.LogMap;
import com.team.entity.page.PageModel;
import com.team.entity.page.PageParams;
import com.team.entity.system.Log;
import com.team.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LogMap logMap;
    @Autowired
    private LogDao logDao;

    @Override
    public PageDto<LogDto> getLogPageListBy(PageParams params, QueryWrapper<Log> query) {
        if (query == null) {
            query = new QueryWrapper<>();
        }
        PageHelper.startPage(params.getPageIndex(), params.getPageSize());
        return logMap.toPageDto(new PageModel<>(logDao.selectLogList()));
    }
}
