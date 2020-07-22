package com.team.application;

import com.jd.platform.async.executor.Async;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;
import com.team.application.async.DeWorker;
import com.team.application.async.DeWorker1;
import com.team.application.async.DeWorker2;
import com.team.application.async.User;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class AsyncToolTest {

    /**
     * first -> second -> third
     *
     * @author bruce
     */
    @Test
    public void one() throws ExecutionException, InterruptedException {

        DeWorker w = new DeWorker();
        DeWorker1 w1 = new DeWorker1();
        DeWorker2 w2 = new DeWorker2();

        WorkerWrapper<WorkResult<User>, String> workerWrapper2 = new WorkerWrapper.Builder<WorkResult<User>, String>()
                .worker(w2)
                .callback(w2)
                .id("third")
                .build();

        WorkerWrapper<WorkResult<User>, User> workerWrapper1 = new WorkerWrapper.Builder<WorkResult<User>, User>()
                .worker(w1)
                .callback(w1)
                .id("second")
                .next(workerWrapper2)
                .build();

        WorkerWrapper<String, User> workerWrapper = new WorkerWrapper.Builder<String, User>()
                .worker(w)
                .param("0")
                .id("first")
                .next(workerWrapper1, true)
                .callback(w)
                .build();

        WorkResult<User> result = workerWrapper.getWorkResult();
        WorkResult<User> result1 = workerWrapper1.getWorkResult();
        workerWrapper1.setParam(result);
        workerWrapper2.setParam(result1);

        Async.beginWork(3500, workerWrapper);

        System.out.println(workerWrapper2.getWorkResult());
        Async.shutDown();
    }
}
