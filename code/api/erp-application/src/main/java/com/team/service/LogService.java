package com.team.service;

import com.team.core.universal.MongoBaseService;
import com.team.domain.entity.LogEntity;
import com.team.entity.dto.LogDTO;
import com.team.entity.dto.PageDTO;
import com.team.model.SeachLogModel;

/**
 * @author 陈昭宇
 * @description Log 服务类
 * @since 2018-10-28
 */
public interface LogService extends MongoBaseService<LogEntity> {

    PageDTO<LogDTO> getLogPageListBy(SeachLogModel seach);


}
