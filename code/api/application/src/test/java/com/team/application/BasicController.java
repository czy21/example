package com.team.application;

import com.team.application.task.TaskParallel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("basic")
public class BasicController {

    @Autowired
    private TaskParallel taskParallel;

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

}
