package com.team.configure;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.team.entity.mybatis.system.WorkNode;
import com.team.repository.mybatis.system.WorkNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class initProject implements ApplicationRunner {

    @Autowired
    private WorkNodeRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        WorkNode node = new WorkNode();
        node.setLaunchDate(LocalDateTime.now());
        node.setAddedTime(LocalDateTime.now());
        node.setModifiedTime(LocalDateTime.now());
        node.setSequenceValue(IdWorker.getId());
        repository.insert(node);
    }
}
