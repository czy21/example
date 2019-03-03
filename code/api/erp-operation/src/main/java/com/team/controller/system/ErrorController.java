package com.team.controller.system;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
@RestController
public class ErrorController {
    @RequestMapping(path = "/unauthorized/{message}")
    public Object unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return message;
    }
}
