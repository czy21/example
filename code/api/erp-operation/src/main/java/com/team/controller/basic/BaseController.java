package com.team.controller.basic;

import com.team.repository.mongo.system.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {


    @Autowired
    private LogRepository dao;

    @GetMapping("/")
    public String Index() {
        return "主页";
    }

    @GetMapping("csrf")
    public String Csrf() {
        return "csrf";
    }



}
