package com.team.controller.system;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BasicController {
    @RequestMapping(path = "/unauthorized/{message}")
    public Object unauthorized(@PathVariable String message) {
        return message;
    }
}
