package com.team.application.controller;

import com.team.application.model.MachineDTO;
import com.team.application.service.RinseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "machine")
public class MachineController {


    @Autowired
    private RinseService rinseService;

    //curl localhost:8075/machine/state -X POST -d '{"processId":"1"}' --header "Content-Type: application/json"
    @PostMapping(path = "state")
    public Map<String, Object> match(@RequestBody MachineDTO dto) throws Exception {
        return rinseService.sendEvent(dto);
    }

}
