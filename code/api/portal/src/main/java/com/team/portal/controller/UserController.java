package com.team.portal.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchVO;
import com.team.application.option.GenderKind;
import com.team.application.option.SpecialPerson;
import com.team.application.option.SpecialWoman;
import com.team.application.option.YesNoKind;
import com.team.application.service.UserService;
import com.team.cooperated.annotation.EnumOption;
import com.team.cooperated.annotation.SpecialOption;
import com.team.cooperated.controller.BaseController;
import com.team.portal.model.UserExportDTO;
import lombok.Data;
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
import java.time.LocalDate;
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
    @EnumOption(value = {
            GenderKind.class,
            YesNoKind.class
    })
    @SpecialOption(value = {
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
        String filename = URLEncoder.encode("你好", StandardCharsets.UTF_8.toString()) + ".xlsx";
        return CompletableFuture.supplyAsync(() -> downloadExcel(institutionMappingListExportDTOS, UserExportDTO.class, filename));
    }


    @PostMapping(path = "upload")
    public CompletableFuture<ResponseEntity<byte[]>> upload(MultipartFile file, Map<String, Object> param) throws Exception {
        String filename = URLEncoder.encode("模板", StandardCharsets.UTF_8.toString()) + ".xlsx";
        return CompletableFuture.supplyAsync(() -> downloadExcel(List.of(), UserExportDTO.class, filename));
    }

    @Data
    public static class TestDTO {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime birthday;
        private LocalDate birth;
        private LocalDateTime startTime;
    }

    @PostMapping(path = "testTime")
    public TestDTO testTime(@RequestBody TestDTO param) {
        if (param.getBirth() == null) {
            param.setBirth(LocalDate.now());
        }
        if (param.getBirthday() == null) {
            param.setBirthday(LocalDateTime.now());
        }
//        if (param.getStartTime() == null) {
//            param.setStartTime(LocalDateTime.now());
//        }
        return param;
    }


}

