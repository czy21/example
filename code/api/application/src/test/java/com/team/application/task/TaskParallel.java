package com.team.application.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class TaskParallel {

    @Async
    public CompletableFuture<String> task1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        System.out.println("task 1 耗时: " + (endTime - startTime));
        return CompletableFuture.completedFuture("task 1 执行完毕");
    }

    @Async
    public CompletableFuture<String> task2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        System.out.println("task 2 耗时: " + (endTime - startTime));
        return CompletableFuture.completedFuture("task 2 执行完毕");
    }

    @Async
    public CompletableFuture<String> task3() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(3000);
        long endTime = System.currentTimeMillis();
        System.out.println("task 3 耗时: " + (endTime - startTime));
        return CompletableFuture.completedFuture("task 3 执行完毕");
    }
}
