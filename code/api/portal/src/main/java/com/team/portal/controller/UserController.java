package com.team.portal.controller;


import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchVO;
import com.team.application.pocket.EnumGender;
import com.team.application.pocket.SpecialPerson;
import com.team.application.pocket.SpecialWoman;
import com.team.application.service.UserService;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    @Autowired
    @Qualifier(value = "rinseJob")
    Job rinseJob;
    @Autowired
    JobLauncher jobLauncher;


    @GetMapping(path = "load")
    @EnumPocket(value = {
            EnumGender.class,
    })
    @SpecialPocket(value = {
            SpecialPerson.class,
            SpecialWoman.class
    })
    public PageDTO<UserDTO> load() {
        return new PageDTO<>();
    }

    @PostMapping(path = "search")
    public PageDTO<UserDTO> search(@RequestBody SearchVO<UserDTO> search) {

        return userService.findByPage(search);
    }

    @PostMapping(path = "send")
    public Map<String, Object> send() {
        ProducerRecord<String, Map<String, Object>> msg = new ProducerRecord<>("topic1", Map.of("name", "陈昭宇"));
        kafkaTemplate.send(msg);
        return Map.of();
    }

    @GetMapping(path = "submitJob")
    public Map<String, Object> submitJob() throws Exception {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addDate("commitDate", new Date());
        jobLauncher.run(rinseJob, parametersBuilder.toJobParameters());
        return Map.of();
    }

}

