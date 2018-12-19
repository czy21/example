package com.team.controller.system;

import com.team.core.configure.Pocket;
import com.team.dao.system.LogDao;
import com.team.entity.dto.LogDto;
import com.team.entity.dto.PageDto;
import com.team.entity.dto.UserDto;
import com.team.entity.map.LogMap;
import com.team.entity.page.PageParams;
import com.team.entity.system.Department;
import com.team.model.SearchUserModel;
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
@RequestMapping("api/log")
@Api(tags = "Log", description = "日志操作接口")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("load")
    @ApiOperation(value = "加载日志列表")
    public PageDto<LogDto> Load(PageParams search) {
        return logService.getLogPageListBy(search, null);
    }

    @PostMapping("search")
    @ApiOperation(value = "查询日志列表")
    public PageDto<LogDto> Search(SearchUserModel search) {
        return logService.getLogPageListBy(search, null);
    }

}
