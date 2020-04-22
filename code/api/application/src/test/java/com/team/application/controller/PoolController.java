package com.team.application.controller;

import com.team.application.pool.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "pool")
public class PoolController {
    @Autowired
    private Counter counter;

    //curl -X GET --header 'Accept: application/json''http://localhost:8075/pool/count'

    @GetMapping(path = "count")
    public String count() {
        return "Count: " + counter.count();
    }

}
