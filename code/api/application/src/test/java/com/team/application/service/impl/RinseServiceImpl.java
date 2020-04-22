package com.team.application.service.impl;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import com.team.application.service.RinseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service
public class RinseServiceImpl implements RinseService {

//    @Autowired
    private StateMachine<RinseNode, RinseEvent> stateMachine;

//    @Autowired
    private StateMachinePersister<RinseNode, RinseEvent, String> stateMachinePersister;

    @Override
    public void sendEvent(String processId) throws Exception {
        resetStateMachineFromStore(processId);
        stateMachine.sendEvent(RinseEvent.NORMAL);
        stateMachinePersister.persist(stateMachine, "testprefix:" + processId);
    }

    @Override
    public StateMachine<RinseNode, RinseEvent> resetStateMachineFromStore(String processId) throws Exception {
        return stateMachinePersister.restore(stateMachine, "testprefix:" + processId);
    }

}
