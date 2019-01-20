package com.team.service;

import com.team.core.universal.MongoBaseService;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.mongo.Log;
import com.team.entity.page.PageModel;
import com.team.model.SeachLogModel;
import org.springframework.data.domain.Page;

/**
 * @author 陈昭宇
 * @description Log 服务类
 * @since 2018-10-28
 */
public interface LogService extends MongoBaseService<Log> {

    Page getLogPageListBy(SeachLogModel seach);

}
