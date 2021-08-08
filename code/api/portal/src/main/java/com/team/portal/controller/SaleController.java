package com.team.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.ApplicationConfig;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.application.service.PersistService;
import com.team.application.service.SaleService;
import com.team.cooperated.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RequestMapping(path = "sale")
@RestController
@Slf4j
public class SaleController extends BaseController {

    @Autowired
    MaterialService materialService;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job rinseJob;

    @Autowired
    SaleService saleService;

    @PostMapping(path = "upload")
    public MaterialVO upload(FileVO fileVO) throws IOException {
        MaterialVO materialVO = materialService.upload(fileVO, "DEFAULT");
        kafkaTemplate.send(ApplicationConfig.SPI_FILE_TOPIC, materialVO);
        return materialVO;
    }

    @PostMapping(path = "redisTest")
    public Map<String, Object> redisTest() {
        return Map.of();
    }

    @PostMapping(path = "submitJob")
    public Map<String, Object> submitJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("tableName", "ent_sfl_inspect_sale").addDate("date", new Date()).toJobParameters();
        jobLauncher.run(rinseJob, jobParameters);
        return Map.of();
    }

    @Autowired
    @Qualifier(value = "hbasePersistServiceImpl")
    PersistService hbasePersistService;

    @Autowired
    @Qualifier(value = "mysqlPersistServiceImpl")
    PersistService mysqlPersistService;

    @PostMapping(path = "migrateToHBase")
    public Map<String, Object> migrateToHBase() {
        saleService.migrateToHBase(hbasePersistService);
        return Map.of("status", "success");
    }

    @PostMapping(path = "migrateToMysql")
    public Map<String, Object> migrateToMysql() {
        saleService.migrateToHBase(mysqlPersistService);
        return Map.of("status", "success");
    }

}
