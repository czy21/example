/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.team.application.config;

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import com.team.application.machine.PersistLocal;
import com.team.domain.mapper.OrderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<RinseNode, RinseEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<RinseNode, RinseEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<RinseNode, RinseEvent> states) throws Exception {
        states.withStates()
                .initial(RinseNode.ONE).
                states(EnumSet.allOf(RinseNode.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<RinseNode, RinseEvent> transitions) throws Exception {
        transitions
                .withExternal().source(RinseNode.ONE).target(RinseNode.TWO).action(action1()).event(RinseEvent.NORMAL)
                .and()
                .withExternal().source(RinseNode.TWO).target(RinseNode.THREE).event(RinseEvent.NORMAL)
                .and()
                .withExternal().source(RinseNode.THREE).target(RinseNode.FOUR).event(RinseEvent.NORMAL);
    }


    @Bean
    public Action<RinseNode, RinseEvent> action1() {
        return context -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().toString());
        };
    }

    @Bean
    public StateMachinePersist<RinseNode, RinseEvent, RinseNode> stateMachinePersist(OrderMapper orderMapper) {
        return new PersistLocal(orderMapper);
    }

    @Bean
    public StateMachinePersister<RinseNode, RinseEvent, RinseNode> redisStateMachinePersister(StateMachinePersist<RinseNode, RinseEvent, RinseNode> stateMachinePersist) {
        return new DefaultStateMachinePersister<>(stateMachinePersist);
    }
}
