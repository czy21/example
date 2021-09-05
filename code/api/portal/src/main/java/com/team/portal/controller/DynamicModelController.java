package com.team.portal.controller;

import com.team.domain.mongo.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "dynamicModel")
public class DynamicModelController {

    @Autowired
    InstitutionRepository institutionRepository;

    @PostMapping(path = "search")
    public List<?> search(@RequestBody Map<String, Object> input) {
        String tenantId = (String) input.get("tenantId");
        return institutionRepository.findByTenantId(tenantId);
    }
}
