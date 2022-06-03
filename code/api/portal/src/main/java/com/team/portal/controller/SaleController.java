package com.team.portal.controller;

import com.czy.pulsar.core.PulsarTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.czy.learning.web.controller.BaseController;
import com.team.application.config.QueueConfig;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.application.service.PersistService;
import com.team.application.service.SaleService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

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
    JobLauncher jobLauncher;
    @Autowired
    Job rinseJob;
    @Autowired
    SaleService saleService;
    @Autowired
    PulsarTemplate pulsarTemplate;

    @PostMapping(path = "upload")
    public MaterialVO uploadByRabbit(FileVO fileVO, @RequestParam(value = "ds", required = false) String dataSource) throws Exception {
        MaterialVO materialVO = materialService.upload(fileVO, "OSS");
        materialVO.setTargetDataSource(dataSource);
        rabbitTemplate.convertAndSend(QueueConfig.SPI_FILE_TOPIC, materialVO);
        return materialVO;
    }

    @PostMapping(path = "uploadToPulsar")
    public MaterialVO uploadToPulsar(FileVO fileVO, @RequestParam(value = "ds", required = false) String dataSource) throws Exception {
        MaterialVO materialVO = materialService.upload(fileVO, "OSS");
        materialVO.setTargetDataSource(dataSource);
        pulsarTemplate.send(QueueConfig.SPI_FILE_TOPIC, materialVO);
        return new MaterialVO();
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

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping(path = "redisTestPut")
    public Map<String, Object> redisTestPut(@RequestBody Map<String, Object> param) {
        stringRedisTemplate.opsForValue().set((String) param.get("id"), (String) param.get("value"));
        return Map.of("status", "success");
    }

    @PostMapping(path = "redisTestGet")
    public Map<String, Object> redisTestGet(@RequestParam("key") String key) {
        return Map.of("value", stringRedisTemplate.opsForValue().get(key));
    }

    @PostMapping(path = "test1")
    public Map<String, Object> test1(@RequestBody TestModel param) {
        return Map.of();
    }

    @Data
    public static class TestModel {
        private String name;
        private Integer age;
    }

}
