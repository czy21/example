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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

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
                    .initial("PLACED")
                    .state("PROCESSING")
                    .state("SENT")
                    .state("DELIVERED");
        }

        @Override
        public void configure(StateMachineTransitionConfigurer<String, String> transitions)
                throws Exception {
            transitions
                    .withExternal()
                    .source("PLACED").target("PROCESSING")
                    .event("PROCESS")
                    .and()
                    .withExternal()
                    .source("PROCESSING").target("SENT")
                    .event("SEND")
                    .and()
                    .withExternal()
                    .source("SENT").target("DELIVERED")
                    .event("DELIVER");
        }
    }
}
