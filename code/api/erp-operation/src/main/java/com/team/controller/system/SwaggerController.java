package com.team.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/swagger")
@Api(tags = "Menu", description = "Swagger操作接口")
public class SwaggerController extends Swagger2Controller {

    @Autowired
    private HttpServletRequest request;

    public SwaggerController(Environment environment, DocumentationCache documentationCache, ServiceModelToSwagger2Mapper mapper, JsonSerializer jsonSerializer) {
        super(environment, documentationCache, mapper, jsonSerializer);
    }

    @ApiIgnore
    public ResponseEntity<Json> getDocumentation(@RequestParam(value = "group", required = false) String swaggerGroup, HttpServletRequest servletRequest) {
        return super.getDocumentation(swaggerGroup, servletRequest);
    }

    @GetMapping("docs")
    @ApiOperation("Api接口列表")
    public Object Docs() {
        return getDocumentation(null, request);
    }
}
