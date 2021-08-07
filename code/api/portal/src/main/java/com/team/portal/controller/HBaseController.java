package com.team.portal.controller;

import com.team.application.service.impl.HBaseService;
import com.team.cooperated.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping(path = "hbase")
public class HBaseController extends BaseController {

    public static final String NAMESPACE = "ioe_ns";

    public static final String TABLE_NAME = "member";

    @Autowired
    private HBaseService hBaseService;

    @PostMapping(path = "create")
    public Map<String,Object> create() {
        List<String> cf = new ArrayList<>();
        cf.add("name");
        cf.add("address");
        hBaseService.createTable(NAMESPACE + ":" + TABLE_NAME, cf);
        return Map.of("status","success");
    }
}
