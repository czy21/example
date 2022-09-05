package com.team.portal.demo.controller;

import com.team.portal.demo.annotation.Encrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(path = "entry1")
@RestController
public class Entry1Controller {

    @GetMapping(path = "func1")
    public Map<String, Object> func1(@Encrypt @RequestParam("name") String name) {
        return Map.of();
    }
}
