package com.team.operation.controller;

import com.team.application.core.annotations.NoLog;
import com.team.application.model.dto.LogDTO;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.vo.SeachLogModel;
import com.team.application.service.LogService;
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
    public PageDTO<LogDTO> Load(SeachLogModel search) {
        return logService.getLogPageListBy(search);
    }

    @NoLog
    @PostMapping("search")

    //@PreAuthorize("hasAuthority('SearchLog')")
    public PageDTO<LogDTO> Search(SeachLogModel search) {
        return logService.getLogPageListBy(search);
    }
}