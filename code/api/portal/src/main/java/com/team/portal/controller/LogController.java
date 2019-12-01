package com.team.portal.controller;

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

    @GetMapping("load")

    public PageDTO<LogDTO> load(SeachLogModel search) {
        return logService.getLogPageListBy(search);
    }

    @PostMapping("search")
    public PageDTO<LogDTO> search(SeachLogModel search) {
        return logService.getLogPageListBy(search);
    }
}
