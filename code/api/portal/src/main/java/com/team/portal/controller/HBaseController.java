package com.team.portal.controller;

import com.team.application.service.TableMetadataService;
import com.team.application.service.impl.HBaseService;
import com.team.application.service.impl.HbasePersistServiceImpl;
import com.team.cooperated.controller.BaseController;
import com.team.domain.mongo.entity.TableMetadataEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "hbase")
public class HBaseController extends BaseController {

    @Autowired
    private HBaseService hBaseService;

    @Autowired
    private TableMetadataService tableMetadataService;

    @PostMapping(path = "createNamespace")
    public Map<String, Object> createNamespace() {
        hBaseService.createNamespace(HbasePersistServiceImpl.NAMESPACE);
        return Map.of("status", "success");
    }

    @PostMapping(path = "createTable")
    public Map<String, Object> createTable(@RequestBody Map<String, Object> input) {
        TableMetadataEntity entity = tableMetadataService.findOne(HbasePersistServiceImpl.NAMESPACE, HbasePersistServiceImpl.TABLE_NAME);
        hBaseService.createTable(StringUtils.join(List.of(entity.getNamespace(), entity.getTableName()), ":"), new ArrayList<>(entity.getColumnFamily().keySet()));
        return Map.of("status", "success");
    }

    @PostMapping(path = "save")
    public Map<String, Object> save() {
        hBaseService.save(HbasePersistServiceImpl.NAMESPACE + ":" + HbasePersistServiceImpl.TABLE_NAME, "1", Map.of("name", Map.of("1", "陈", "2", "昭宇0")));
        return Map.of("status", "success");
    }

    @PostMapping(path = "get")
    public Map<String, Object> get() {
        return hBaseService.get(HbasePersistServiceImpl.NAMESPACE + ":" + HbasePersistServiceImpl.TABLE_NAME, "1");
    }

}
