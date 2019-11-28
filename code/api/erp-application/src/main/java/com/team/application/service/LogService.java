package com.team.application.service;

import com.team.application.core.universal.MongoBaseService;
import com.team.domain.entity.LogEntity;
import com.team.application.model.dto.LogDTO;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.vo.SeachLogModel;

/**
 * @author 陈昭宇
 * @description Log 服务类
 * @since 2018-10-28
 */
public interface LogService extends MongoBaseService<LogEntity> {

    PageDTO<LogDTO> getLogPageListBy(SeachLogModel seach);


}
