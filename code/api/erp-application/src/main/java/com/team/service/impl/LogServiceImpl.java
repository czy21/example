package com.team.service.impl;

import com.team.core.universal.MongoBaseServiceImpl;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.LogMap;
import com.team.entity.mongo.Log;
import com.team.entity.page.PageModel;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl extends MongoBaseServiceImpl<Log> implements LogService {

    @Resource
    private LogMap logMap;

    @Override
    public Page getLogPageListBy(SeachLogModel search) {
        Page page= mongoRepository.findAll(PageRequest.of(search.getPageIndex() - 1, search.getPageSize()));
        return page;
//        return logMap.toPageDto(new PageModel<>(search.getPageIndex(), search.getPageSize(), list));
    }
}
