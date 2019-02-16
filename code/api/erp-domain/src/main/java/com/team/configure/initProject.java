package com.team.configure;

import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class initProject implements ApplicationRunner {

    @Autowired
    private WorkerNodeDAO dao;

    @Override
    public void run(ApplicationArguments args) {
        dao.updateWorkerNode(UidGenService.getUid());
    }
}
