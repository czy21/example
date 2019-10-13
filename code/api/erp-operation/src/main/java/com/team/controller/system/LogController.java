package com.team.controller.system;

import com.team.core.annotations.NoLog;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.model.SeachLogModel;
import com.team.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈昭宇
 * @description Log 前端控制器
 * @date 2018-09-24
 */
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @NoLog
    @GetMapping("load")

    //@PreAuthorize("hasAuthority('SearchLog')")
    public PageDto<LogDto> Load(SeachLogModel search) {
        return logService.getLogPageListBy(search);
    }

    @NoLog
    @PostMapping("search")

    //@PreAuthorize("hasAuthority('SearchLog')")
    public PageDto<LogDto> Search(SeachLogModel search) {
        return logService.getLogPageListBy(search);
    }
}
