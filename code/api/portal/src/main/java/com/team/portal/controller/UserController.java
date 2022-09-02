package com.team.portal.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.czy.learning.web.controller.BaseController;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchVO;
import com.team.application.service.UserService;
import com.team.portal.model.UserExportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    @Qualifier(value = "rinseJob")
    Job rinseJob;
    @Autowired
    JobLauncher jobLauncher;


    @GetMapping(path = "load")
    public PageDTO<UserDTO> load() {
        return new PageDTO<>();
    }

    @PostMapping(path = "search")
    public PageDTO<UserDTO> search(@RequestBody SearchVO<UserDTO> search) {

        return userService.findByPage(search);
    }

    @GetMapping(path = "submitJob")
    public Map<String, Object> submitJob() throws Exception {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addDate("commitDate", new Date());
        jobLauncher.run(rinseJob, parametersBuilder.toJobParameters());
        return Map.of();
    }

    @PostMapping(path = "export")
    public CompletableFuture<ResponseEntity<byte[]>> export() throws Exception {
        List<UserExportDTO> institutionMappingListExportDTOS = List.of(UserExportDTO.builder().name("你好").build());
        String filename = URLEncoder.encode("你好", StandardCharsets.UTF_8) + ".xlsx";
        return CompletableFuture.supplyAsync(() -> downloadExcel(institutionMappingListExportDTOS, UserExportDTO.class, filename));
    }


    @PostMapping(path = "upload")
    public CompletableFuture<ResponseEntity<byte[]>> upload(MultipartFile file, Map<String, Object> param) throws Exception {
        String filename = URLEncoder.encode("模板", StandardCharsets.UTF_8) + ".xlsx";
        return CompletableFuture.supplyAsync(() -> downloadExcel(List.of(), UserExportDTO.class, filename));
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestDTO {

        private LocalDateTime t1;
//        private LocalDateTime t2;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime t3;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime t4;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime t5;

        private Date t6;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:s")
        private Date t7;

    }

    @PostMapping(path = "testTime")
    public TestDTO testTime(@RequestBody TestDTO param) throws Exception {
        if (param.getT1() == null) {
            param.t1 = LocalDateTime.now();
        }
//        if (param.getT2() == null) {
//            param.t2 = LocalDateTime.now();
//        }
        if (param.getT3() == null) {
            param.t3 = LocalDateTime.now();
        }
        if (param.getT4() == null) {
            param.t4 = LocalDateTime.now();
        }
        if (param.getT5() == null) {
            param.t5 = LocalDateTime.now();
        }

        if (param.getT6() == null) {
            param.t6 = new Date();
        }

        if (param.getT7() == null) {
            param.t7 = new Date();
        }
        return param;
    }


}

