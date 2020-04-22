package com.team.application.service;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import org.springframework.statemachine.StateMachine;

import java.util.Map;

public interface RinseService {


    Map<String,Object> sendEvent(String processId) throws Exception;

    StateMachine<RinseNode, RinseEvent> resetStateMachineFromStore(String processId) throws Exception;

}
