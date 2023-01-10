package com.team.orm.controller;


import com.team.orm.entity.UserPO;
import com.team.orm.service.CacheService;
import com.team.orm.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "user")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    CacheService cacheService;

    @PostMapping(path = "search")
    public List<UserPO> search() {
        return userService.findAll();
    }

    @PostMapping(path = "cache/put")
    public void cachePut() {
        userService.put(UUID.randomUUID().toString(), new byte[2 * 1024 * 1024]);
    }

    @Value("classpath:user.json")
    Resource resource;

    @GetMapping(path = "getConfig")
    public Map<String, Object> getConfig() throws IOException {
        InputStream a = resource.getInputStream();
        String str1 = IOUtils.toString(a);
        return new HashMap<>();
    }

    @GetMapping(path = "testCache")
    public Map<String, Object> testCache(@RequestParam String name) {
        return cacheService.getUser(name);
    }
}
