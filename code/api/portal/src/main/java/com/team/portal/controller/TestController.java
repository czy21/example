package com.team.portal.controller;

import com.czy.learning.pulsar.core.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "test")
@RestController
public class TestController {

    @Autowired
    PulsarTemplate pulsarTemplate;

    @PostMapping(path = "pulsar/put")
    public Map<String, Object> pulsarPut(@RequestParam String topic, @RequestBody Map<String, Object> param) throws PulsarClientException {
        pulsarTemplate.send(topic, param);
        return Map.of();
    }
}
