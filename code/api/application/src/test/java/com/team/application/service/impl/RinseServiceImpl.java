package com.team.application.service.impl;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import com.team.application.model.MachineDTO;
import com.team.application.service.RinseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RinseServiceImpl implements RinseService {

    @Autowired
    private StateMachineFactory<RinseNode, RinseEvent> stateMachineFactory;

    @Autowired
    private StateMachinePersister<RinseNode, RinseEvent, RinseNode> stateMachinePersister;

    @Override
    public Map<String, Object> sendEvent(MachineDTO dto) throws Exception {
        StateMachine<RinseNode, RinseEvent> stateMachine = getOrRestore(dto.getNode());
        stateMachine.sendEvent(RinseEvent.NORMAL);
        return Map.of("uuid", stateMachine.getUuid().toString(), "state", stateMachine.getState().getId());
    }

    public StateMachine<RinseNode, RinseEvent> getOrRestore(RinseNode node) throws Exception {
        StateMachine<RinseNode, RinseEvent> stateMachine = stateMachineFactory.getStateMachine();
        return node == null ? stateMachine : stateMachinePersister.restore(stateMachine, node);
    }

}
