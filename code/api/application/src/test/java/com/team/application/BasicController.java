package com.team.application;

import com.team.application.task.TaskParallel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("basic")
public class BasicController {

    @Autowired
    private TaskParallel taskParallel;

    @Autowired
    private StateMachineFactory<String, String> stateMachineFactory;

    @Autowired
    private StateMachinePersister<String, String, String> stateMachinePersister;

    @GetMapping("parallelTest")
    public void taskParallelTest() throws InterruptedException {
//        long startTime = System.currentTimeMillis();
//        CompletableFuture<String> task1 = taskParallel.task1();
//        CompletableFuture<String> task2 = taskParallel.task2();
//        CompletableFuture<String> task3 = taskParallel.task3();
//        CompletableFuture.allOf(task1, task2, task3).join();
//        long endTime = System.currentTimeMillis();
//        System.out.println("总耗时: " + (endTime - startTime));

        List<String> whats = List.of("1", "2", "3");
        whats.parallelStream().forEach(System.out::println);
    }

    @GetMapping("machineTest")
    public void machineTest() throws Exception {
        List<CompletableFuture<StateMachine<String, String>>> machineThreads = new ArrayList<>();

        List<StateMachine<String, String>> machines = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String id = Integer.toString(i);
            StateMachine<String, String> machine = stateMachinePersister.restore(stateMachineFactory.getStateMachine(id), id);
            machine.stop();
            machine.start();
            stateMachinePersister.persist(machine, id);
            machines.add(machine);
        }

        System.out.println("a");
    }

}
