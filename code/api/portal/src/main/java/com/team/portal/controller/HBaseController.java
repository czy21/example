package com.team.portal.controller;


import com.czy.learning.web.controller.BaseController;
import com.team.application.service.HBaseService;
import com.team.application.service.TableMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "hbase")
public class HBaseController extends BaseController {

    @Autowired(required = false)
    private HBaseService hBaseService;

    @Autowired
    private TableMetadataService tableMetadataService;

//    @PostMapping(path = "createNamespace")
//    public Map<String, Object> createNamespace() {
//        hBaseService.createNamespace(HbasePersistServiceImpl.NAMESPACE);
//        return Map.of("status", "success");
//    }
//
//    @PostMapping(path = "createTable")
//    public Map<String, Object> createTable(@RequestBody Map<String, Object> input) {
//        HBaseTableMetadataEntity entity = tableMetadataService.findOne(HbasePersistServiceImpl.NAMESPACE, HbasePersistServiceImpl.TABLE_NAME);
//        hBaseService.createTable(StringUtils.join(List.of(entity.getNamespace(), entity.getTableName()), ":"), new ArrayList<>(entity.getColumnFamily().keySet()));
//        return Map.of("status", "success");
//    }
//
//    @PostMapping(path = "save")
//    public Map<String, Object> save() {
//        hBaseService.save(HbasePersistServiceImpl.NAMESPACE + ":" + HbasePersistServiceImpl.TABLE_NAME, "1", Map.of("name", Map.of("1", "陈", "2", "昭宇0")));
//        return Map.of("status", "success");
//    }
//
//    @PostMapping(path = "get")
//    public Map<String, Object> get() {
//        return hBaseService.get(HbasePersistServiceImpl.NAMESPACE + ":" + HbasePersistServiceImpl.TABLE_NAME, "1");
//    }
//
//
//    @PostMapping(path = "countSale")
//    public Map<String,Object> countSale(){
//        var count=hBaseService.count(HbasePersistServiceImpl.NAMESPACE + ":" + HbasePersistServiceImpl.TABLE_NAME);
//        return Map.of("count",count);
//    }

    @PostMapping(path = "testp")
    public Map<String, Object> testP() {
        var m = new HashMap<String, Object>();
        m.put("name", "sss");
        m.put("age", null);

        return m;
    }
}
