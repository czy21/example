package com.team.portal.controller;

import com.team.application.client.DynamicDemoClient;
import com.team.domain.mongo.repository.InstitutionRepository;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.spi.ObjectFactoryBuilder;
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

    @PostMapping(path = "save")
    public List<?> save(@RequestBody Map<String, Object> input) {

        return institutionRepository.findByTenantId("");
    }


    @Autowired
    Encoder feignEncoder;
    @Autowired
    Decoder feignDecoder;

    @PostMapping(path = "findByClient")
    public Object findByClient() {
        var p = Feign.builder()
                .encoder(feignEncoder)
                .decoder(feignDecoder)
                .logger(new Slf4jLogger(DynamicDemoClient.class))
                .logLevel(Logger.Level.FULL)
                .target(DynamicDemoClient.class, "http://127.0.0.1:8080/portal/user/search");
        var ret = p.find(Map.of());
        return ret;
    }


}
