package com.team.application.service.impl;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import com.team.application.service.RinseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
//@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RinseServiceImpl implements RinseService {

    @Autowired
    private StateMachine<RinseNode, RinseEvent> stateMachine;

    @Autowired
    private StateMachinePersister<RinseNode, RinseEvent, String> stateMachinePersister;

    @Override
    public Map<String,Object> sendEvent(String processId) {
//        resetStateMachineFromStore(processId);
        stateMachine.sendEvent(RinseEvent.NORMAL);
//        stateMachinePersister.persist(stateMachine, "testprefix:" + processId);
        return Map.of("uuid", stateMachine.getUuid().toString(), "state", stateMachine.getState().getId());
    }

    @Override

    public StateMachine<RinseNode, RinseEvent> resetStateMachineFromStore(String processId) throws Exception {
        return stateMachinePersister.restore(stateMachine, "testprefix:" + processId);
    }

}
