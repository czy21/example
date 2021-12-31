package com.team.portal.controller;


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
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        List<UserExportDTO> institutionMappingListExportDTOS = new ArrayList<>();
        String filename = URLEncoder.encode("hello", StandardCharsets.UTF_8.toString()) + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".xlsx";
        return CompletableFuture.supplyAsync(() -> downloadExcel(institutionMappingListExportDTOS, UserExportDTO.class, filename));
    }

}

