package com.team.controller.system;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @RequestMapping(path = "/unauthorized/{message}")
    public JSONObject unauthorized(@PathVariable String message) {
        JSONObject json = new JSONObject();
        json.put("data", JSONObject.parseObject(message));
        return json;
    }

    @GetMapping("api/test")
    public Object test() {
        return "hello";
    }
}
