package com.team.service.impl;

import com.team.core.universal.MongoBaseServiceImpl;
import com.team.entity.dto.LogDTO;
import com.team.entity.dto.PageDTO;
import com.team.entity.map.LogAutoMap;
import com.team.domain.entity.LogEntity;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl extends MongoBaseServiceImpl<LogEntity> implements LogService {

    @Resource
    private LogAutoMap logMap;

    @Override
    public PageDTO<LogDTO> getLogPageListBy(SeachLogModel search) {
        if (search.getAddedTimeSort() == null) {
            search.setAddedTimeSort("desc");
        }
        LogEntity log = new LogEntity();
        log.setDescription(search.getDescription());
        log.setMethod(search.getMethod());
        log.setExceptionCode(search.getExceptionCode());
        log.setExceptionDetail(search.getExceptionDetail());
        log.setRequestIp(search.getRequestIp());
        log.setLogType(search.getLogType() == null ? null : !search.getLogType());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("description", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("method", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("exceptionDetail", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("exceptionCode", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("requestIp", ExampleMatcher.GenericPropertyMatcher::contains)
                .withIgnoreCase();

        return logMap.mapToPageDto(super.findAll(search.getPageIndex(), search.getPageSize(), Example.of(log, matcher), Sort.Direction.fromString(search.getAddedTimeSort()), "addedTime"));
    }
}
