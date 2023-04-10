package com.team.webdav.controller;

import com.team.webdav.config.ConfigConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "config")
@RestController
public class ConfigController {

    @Autowired
    ConfigConfigurationProperties configConfigurationProperties;

    @GetMapping(path = "load")
    public Map<String, Object> load() {
        Map<String, Object> h = new HashMap<>();
        h.put("name", configConfigurationProperties.getName());
        h.put("name1", configConfigurationProperties.getName1());
        return h;
    }
}
