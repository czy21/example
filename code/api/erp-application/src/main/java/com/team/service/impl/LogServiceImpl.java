package com.team.service.impl;

import com.team.core.universal.MongoBaseServiceImpl;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.LogMap;
import com.team.entity.mongo.Log;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl extends MongoBaseServiceImpl<Log> implements LogService {

    @Resource
    private LogMap logMap;

    @Override
    public PageDto<LogDto> getLogPageListBy(SeachLogModel search) {
        if (search.getAddedTimeSort() == null) {
            search.setAddedTimeSort("desc");
        }
        Log log = new Log();
        log.setDescription(search.getDescription());
        log.setMethod(search.getMethod());
        log.setExceptionCode(search.getExceptionCode());
        log.setExceptionDetail(search.getExceptionDetail());
        log.setRequestIp(search.getRequestIp());
        log.setLogType(search.getLogType() == null ? null : !search.getLogType());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("description", match -> match.contains())
                .withMatcher("method", match -> match.contains())
                .withMatcher("exceptionDetail", match -> match.contains())
                .withMatcher("exceptionCode", match -> match.contains())
                .withMatcher("requestIp", match -> match.contains())
                .withIgnoreCase();

        return logMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), Example.of(log, matcher), Sort.Direction.fromString(search.getAddedTimeSort()), "addedTime"));
    }
}
