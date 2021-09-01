package com.team.portal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

//    @Value("${l}")
//    private String l;
//
//    @RequestMapping("/get")
//    public String get() {
//        return l;
//    }
}