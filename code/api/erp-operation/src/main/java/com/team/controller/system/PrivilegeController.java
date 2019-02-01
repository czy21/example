package com.team.controller.system;

import com.team.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/privilege")
@Api(tags = "Privilege", description = "特权操作接口")
public class PrivilegeController {

    @Autowired
    private LogService logService;

    @PostMapping("migrateLog")
    @ApiOperation(value = "迁移日志至Mongo")
    public Boolean MigrateLog() {
        return logService.migrateLogByMysql();
    }


}