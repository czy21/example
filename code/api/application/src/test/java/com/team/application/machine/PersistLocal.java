package com.team.application.machine;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import com.team.domain.mapper.OrderMapper;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;

public class PersistLocal implements StateMachinePersist<RinseNode, RinseEvent, RinseNode> {

    private OrderMapper orderMapper;

    public PersistLocal(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void write(StateMachineContext<RinseNode, RinseEvent> context, RinseNode contextObj) {
    }

    @Override
    public StateMachineContext<RinseNode, RinseEvent> read(RinseNode contextObj) {
        return new DefaultStateMachineContext<>(contextObj, null, null, null);
    }
}
