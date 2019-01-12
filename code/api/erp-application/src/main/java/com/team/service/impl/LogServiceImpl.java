package com.team.service.impl;

import com.team.core.universal.MongoBaseServiceImpl;
import com.team.entity.dto.PageDto;
import com.team.entity.mongo.Log;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends MongoBaseServiceImpl<Log> implements LogService {

    @Override
    public PageDto<Log> getDepartmentPageListBy(SeachLogModel search) {

        return null;
    }
}
