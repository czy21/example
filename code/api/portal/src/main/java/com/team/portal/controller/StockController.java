package com.team.portal.controller;


import com.team.application.model.dto.StockDTO;
import com.team.application.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "stock")
public class StockController {


    @Autowired
    StockService stockService;

    @PostMapping(path = "add")
    public Map<String, Object> update(@RequestBody StockDTO input) {
        stockService.add(input);
        return Map.of("status", "success");
    }

    @PostMapping(path = "sale")
    public Map<String, Object> detail(@RequestBody StockDTO input) {
        stockService.reduce(input);
        return Map.of("status", "success");
    }

    @PostMapping(path = "gcTest1")
    public Map<String, Object> gcTest1() throws InterruptedException {
        List<byte[]> lists = new ArrayList<>();
        while (true) {
            Thread.sleep(1000 * 2);
            lists.add(new byte[10 * 1024*1024]);
        }
    }

}
