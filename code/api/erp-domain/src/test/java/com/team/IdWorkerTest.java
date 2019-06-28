package com.team;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.team.entity.mybatis.system.WorkNode;
import com.team.repository.mybatis.system.WorkNodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
public class IdWorkerTest {

    @Autowired
    private WorkNodeRepository repository;

    @Test
    public void initIdWorkerTest() {

        WorkNode node = new WorkNode();
        node.setLaunchDate(LocalDateTime.now());
        node.setAddedTime(LocalDateTime.now());
        node.setModifiedTime(LocalDateTime.now());
        node.setSequenceValue(IdWorker.getId());
        repository.insert(node);

        System.out.println("aa");
    }
}
