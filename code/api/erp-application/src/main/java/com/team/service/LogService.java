package com.team.service;

import com.team.core.universal.MongoBaseService;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.LogEntity;
import com.team.model.SeachLogModel;

/**
 * @author 陈昭宇
 * @description Log 服务类
 * @since 2018-10-28
 */
public interface LogService extends MongoBaseService<LogEntity> {

    PageDto<LogDto> getLogPageListBy(SeachLogModel seach);


}
