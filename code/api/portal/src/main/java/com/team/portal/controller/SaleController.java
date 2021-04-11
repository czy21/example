package com.team.portal.controller;

import com.alibaba.excel.EasyExcel;
import com.team.application.excel.FileListener;
import com.team.application.model.vo.FileVO;
import com.team.cooperated.controller.BaseController;
import com.team.domain.mongo.repository.FileColumnMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "sale")
@RestController
@Slf4j
public class SaleController extends BaseController {

    @Autowired
    RedisTemplate<String, Map<String, Object>> stringListRedisTemplate;

    @Autowired
    FileColumnMappingRepository fileColumnMappingRepository;

    @PostMapping(path = "upload")
    public Map<String, Object> upload(FileVO fileVO) throws IOException {

        LocalDateTime start = LocalDateTime.now();

        EasyExcel.read(fileVO.getFile().getInputStream(),
                new FileListener(stringListRedisTemplate,
                        fileColumnMappingRepository
                )).sheet().doRead();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        log.info(duration.toMinutes() + "m:" + duration.toMillis() + "s");
        return new HashMap<>();
    }
}
