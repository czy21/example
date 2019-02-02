package com.team.service.impl;

import com.team.core.universal.MongoBaseServiceImpl;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.LogMap;
import com.team.entity.mongo.Log;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import com.team.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl extends MongoBaseServiceImpl<Log> implements LogService {

    @Resource
    private LogMap logMap;
    @Resource
    private UserService userService;


    @Override
    public PageDto<LogDto> getLogPageListBy(SeachLogModel search) {
        return logMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }
}
