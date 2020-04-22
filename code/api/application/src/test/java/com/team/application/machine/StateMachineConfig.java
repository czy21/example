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

import com.team.application.kind.RinseEvent;
import com.team.application.kind.RinseNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;

import java.util.EnumSet;

@Configuration
public class StateMachineConfig {

//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public ProxyFactoryBean stateMachine() {
//        ProxyFactoryBean pfb = new ProxyFactoryBean();
//        pfb.setTargetSource(poolTargetSource());
//        return pfb;
//    }
//
//    @Bean
//    public CommonsPool2TargetSource poolTargetSource() {
//        CommonsPool2TargetSource pool = new CommonsPool2TargetSource();
//        pool.setMaxSize(2);
//        pool.setTargetBeanName("stateMachineTarget");
//        return pool;
//    }


    @Bean(name = "stateMachineTarget")
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public StateMachine<RinseNode, RinseEvent> stateMachineTarget() throws Exception {
        StateMachineBuilder.Builder<RinseNode, RinseEvent> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(RinseNode.ONE)
                .states(EnumSet.allOf(RinseNode.class));

        builder.configureTransitions()
                .withExternal().source(RinseNode.ONE).target(RinseNode.TWO).action(action1()).event(RinseEvent.NORMAL)
                .and()
                .withExternal().source(RinseNode.TWO).target(RinseNode.THREE).event(RinseEvent.NORMAL)
                .and()
                .withExternal().source(RinseNode.THREE).target(RinseNode.FOUR).event(RinseEvent.NORMAL);

        return builder.build();
    }

    @Bean
    public Action<RinseNode, RinseEvent> action1() {
        return new Action<RinseNode, RinseEvent>() {
            @Override
            public void execute(StateContext<RinseNode, RinseEvent> context) {
                System.out.println(Thread.currentThread().toString());
//                try {
//                    TimeUnit.SECONDS.sleep(20);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public StateMachinePersist<RinseNode, RinseEvent, String> stateMachinePersist(RedisConnectionFactory connectionFactory) {
        RedisStateMachineContextRepository<RinseNode, RinseEvent> repository =
                new RedisStateMachineContextRepository<>(connectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }

    @Bean
    public RedisStateMachinePersister<RinseNode, RinseEvent> redisStateMachinePersister(
            StateMachinePersist<RinseNode, RinseEvent, String> stateMachinePersist) {
        return new RedisStateMachinePersister<>(stateMachinePersist);
    }
}
