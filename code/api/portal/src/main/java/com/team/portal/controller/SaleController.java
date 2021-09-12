package com.team.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.application.config.QueueConfig;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.application.service.PersistService;
import com.team.application.service.SaleService;
import com.team.cooperated.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @PostMapping(path = "uploadByKafka")
    public MaterialVO uploadByKafka(FileVO fileVO) throws IOException {
        MaterialVO materialVO = materialService.upload(fileVO, "DEFAULT");
        kafkaTemplate.send(QueueConfig.SPI_FILE_TOPIC, materialVO);
        return materialVO;
    }

    @PostMapping(path = "uploadByRabbit")
    public MaterialVO uploadByRabbit(FileVO fileVO) throws IOException {
        MaterialVO materialVO = materialService.upload(fileVO, "DEFAULT");
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(QueueConfig.SPI_FILE_TOPIC, materialVO);
        return materialVO;
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

    @Autowired
    @Qualifier(value = "postgresqlPersistServiceImpl")
    PersistService postgresqlPersistService;

    @PostMapping(path = "migrateToHBase")
    public Map<String, Object> migrateToHBase() {
        saleService.migrateToPersist(hbasePersistService);
        return Map.of("status", "success");
    }

    @PostMapping(path = "migrateToMysql")
    public Map<String, Object> migrateToMysql() {
        saleService.migrateToPersist(mysqlPersistService);
        return Map.of("status", "success");
    }

    @PostMapping(path = "migrateToPostgresql")
    public Map<String, Object> migrateToPostgresql() {
        saleService.migrateToPersist(postgresqlPersistService);
        return Map.of("status", "success");
    }

    @PostMapping(path = "svcTest")
    public Map<String, Object> svcTest() {
        log.info("this is svc");
        return Map.of("status", "success");
    }

}
