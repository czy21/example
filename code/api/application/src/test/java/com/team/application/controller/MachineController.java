package com.team.application.controller;

import com.team.application.model.MachineDTO;
import com.team.application.service.RinseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "machine")
public class MachineController {


    @Autowired
    private RinseService rinseService;

    @PostMapping(path = "state")
    public void match(@RequestBody MachineDTO dto) throws Exception {
        rinseService.sendEvent(dto.getProcessId());
    }

}
