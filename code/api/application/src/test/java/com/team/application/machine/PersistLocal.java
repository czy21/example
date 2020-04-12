package com.team.application.machine;

import com.team.domain.entity.OrderEntity;
import com.team.domain.mapper.OrderMapper;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;

public class PersistLocal implements StateMachinePersist<String, String, String> {

    private OrderMapper orderMapper;

    public PersistLocal(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void write(StateMachineContext<String, String> context, String contextObj) {
        OrderEntity entity = orderMapper.selectById(contextObj);
        if (entity == null) {
            return;
        }
        entity.setState(context.getState());
        orderMapper.updateById(entity);
    }

    @Override
    public StateMachineContext<String, String> read(String contextObj) {

        OrderEntity entity = orderMapper.selectById(contextObj);
        if (entity == null) {
            return null;
        }

        return new DefaultStateMachineContext<>(entity.getState(), null, null, null, null);
    }
}
