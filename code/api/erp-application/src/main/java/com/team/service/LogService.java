package com.team.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.core.universal.BaseService;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.page.PageParams;
import com.team.entity.system.Log;
import com.team.model.SeachLogModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 陈昭宇
 * @description Log 服务类
 * @since 2018-10-28
 */
public interface LogService {

    Object getLogPageListBy(SeachLogModel search);
}
