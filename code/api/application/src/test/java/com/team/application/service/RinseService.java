package com.team.application.service;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import org.springframework.statemachine.StateMachine;

public interface RinseService {


    void sendEvent(String processId) throws Exception;

    StateMachine<RinseNode, RinseEvent> resetStateMachineFromStore(String processId) throws Exception;

}
