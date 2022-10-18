package com.team.stream.controller;

import com.team.stream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping(path = "syncInsert")
    public Integer syncInsert(@RequestParam("ds") String dataSource,
                              @RequestParam(value = "tableName", required = false) String tableName,
                              @RequestParam("size") Integer size) {
        userService.syncInsert(dataSource, Optional.ofNullable(tableName).orElse("user_1"), size);
        return 1;
    }

    @GetMapping(path = "parallelInsert")
    public Integer parallelInsert(@RequestParam("ds") String ds,
                                  @RequestParam("size") Integer size,
                                  @RequestParam(value = "tableName", required = false) String tableName,
                                  @RequestParam(value = "batchSize", required = false) Integer batchSize) {
        userService.parallelInsert(ds, Optional.ofNullable(tableName).orElse("user_1"), size, Optional.ofNullable(batchSize).orElse(500));
        return 1;
    }

    @GetMapping(path = "redisSearch1")
    public Integer redisSearch1() {
        userService.redisSearch();

        return 1;
    }
}
