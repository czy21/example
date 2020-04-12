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
package com.team.application.machine;

import com.team.domain.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class StateMachineConfig {

    @Bean
    public StateMachineLogListener stateMachineLogListener() {
        return new StateMachineLogListener();
    }

    @Configuration
    @EnableStateMachineFactory
    public static class Config extends StateMachineConfigurerAdapter<String, String> {

        @Autowired
        private StateMachineLogListener stateMachineLogListener;

        @Override
        public void configure(StateMachineStateConfigurer<String, String> states)
                throws Exception {
            states
                    .withStates()
                    .initial("1")
                    .state("2")
                    .state("3")
                    .state("4");
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions)
                throws Exception {
            transitions
                    .withExternal()
                    .source("1").target("2")
                    .and()
                    .withExternal()
                    .source("2").target("3")
                    .and()
                    .withExternal()
                    .source("3").target("4");
        }
    }


    @Configuration
    static class PersistConfig {

        @Bean
        public StateMachinePersister<String, String, String> machinePersister(StateMachinePersist<String, String, String> persist) {
            return new DefaultStateMachinePersister<>(persist);
        }

        @Bean
        public StateMachinePersist<String, String, String> machinePersist(OrderMapper orderMapper) {
            return new PersistLocal(orderMapper);
        }

        @Bean
        public StateMachineService<String, String> stateMachineService(StateMachineFactory<String, String> stateMachineFactory, StateMachinePersist<String, String, String> persister) {
            return new DefaultStateMachineService<>(stateMachineFactory, persister);
        }

    }
}
