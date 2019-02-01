package com.team.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.team.core.universal.MongoBaseServiceImpl;
import com.team.core.universal.MybatisBaseService;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.map.LogMap;
import com.team.entity.mongo.Log;
import com.team.model.SeachLogModel;
import com.team.repository.mybatis.MybatisBaseRepository;
import com.team.service.LogService;
import com.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl extends MongoBaseServiceImpl<Log> implements LogService {

    @Resource
    private LogMap logMap;
    @Resource
    private UserService userService;

    @Autowired
    private MybatisBaseRepository<com.team.entity.mybatis.system.Log> mybatisBaseRepository;

    @Override
    public PageDto<LogDto> getLogPageListBy(SeachLogModel search) {
        return logMap.mapToPageDto(super.SelectPageListBy(search.getPageIndex(), search.getPageSize(), null));
    }

    @Override
    public Boolean migrateLogByMysql() {
        try {
            List<com.team.entity.mybatis.system.Log> mybatisLogs = mybatisBaseRepository.selectList(null);
            List<Log> logs = new ArrayList<>();
            mybatisLogs.forEach(t -> {
                Log log = new Log();
                log.setDescription(t.getDescription());
                log.setMethod(t.getMethod());
                log.setLogType(t.getLogType());
                log.setRequestIp(t.getRequestIp());
                log.setExceptionCode(t.getExceptionCode());
                log.setExceptionDetail(t.getExceptionDetail());
                log.setSpendTime(t.getSpendTime());
                log.setOperator(userService.SelectById(t.getUserId()));
                log.setAddedTime(t.getAddedTime());
                logs.add(log);
            });
            super.mongoRepository.insert(logs);
            mybatisBaseRepository.deleteBatchIds(mybatisLogs.stream().map(com.team.entity.mybatis.system.Log::getLogId).collect(Collectors.toList()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
