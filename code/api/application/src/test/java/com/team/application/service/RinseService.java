package com.team.application.service;

import com.team.application.model.MachineDTO;

import java.util.Map;

public interface RinseService {


    Map<String, Object> sendEvent(MachineDTO dto) throws Exception;

}
