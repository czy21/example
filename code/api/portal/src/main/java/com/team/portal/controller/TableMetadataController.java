package com.team.portal.controller;

import com.team.application.service.TableMetadataService;
import com.team.cooperated.controller.BaseController;
import com.team.domain.mongo.entity.TableMetadataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "tableMetadata")
@RestController
@Slf4j
public class TableMetadataController extends BaseController {

    @Autowired
    TableMetadataService tableMetadataService;

    @PostMapping(path = "findAll")
    public TableMetadataEntity findAll() {
        return tableMetadataService.findOne("big_demo", "sale");
    }

    @PostMapping(path = "save")
    public Map<String, Object> save() {
        tableMetadataService.save("big_demo", "sale", Map.of("info", List.of("fromInstitutionCode")));
        return Map.of("status", "success");
    }

}
