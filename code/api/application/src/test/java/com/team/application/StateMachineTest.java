package com.team.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StateMachineTest {

    @Autowired
    private StateMachineFactory<String, String> stateMachineFactory;

    @Test
    public void test1() {


        StateMachine<String, String> machine1 = stateMachineFactory.getStateMachine("1");
        StateMachine<String, String> machine2 = stateMachineFactory.getStateMachine("2");
        StateMachine<String, String> machine3 = stateMachineFactory.getStateMachine("3");
        StateMachine<String, String> machine4 = stateMachineFactory.getStateMachine("4");

        machine1.start();
        machine2.start();
        machine3.start();
        machine4.start();

        machine1.sendEvent("PROCESS");
        machine2.sendEvent("PROCESS");
        machine3.sendEvent("PROCESS");
        machine4.sendEvent("PROCESS");


        System.out.println("aaa");
    }
}