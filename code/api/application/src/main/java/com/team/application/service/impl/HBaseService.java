package com.team.application.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HBaseService {

    private Connection connection;

    public HBaseService(Configuration config) {
        try {
            connection = ConnectionFactory.createConnection(config);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public void createNamespace(String namespace) {
        try (Admin admin = connection.getAdmin()) {
            NamespaceDescriptor desc = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(desc);
            log.info("namespace {} is create success!", namespace);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public void createTable(String tableName, List<String> columnFamily) {
        try (Admin admin = connection.getAdmin()) {
            List<ColumnFamilyDescriptor> cfDescriptor = columnFamily.stream().map(e -> ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(e)).build()).collect(Collectors.toList());
            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName)).setColumnFamilies(cfDescriptor).build();
            if (admin.tableExists(TableName.valueOf(tableName))) {
                log.warn("table {} is exists!", tableName);
            } else {
                admin.createTable(tableDescriptor);
                log.info("table {} is create success!", tableName);
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public void save(String tableName, String rowKey, Map<String, Map<String, String>> data) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowKey));
            data.entrySet().forEach(e -> e.getValue().entrySet().forEach(ee -> {
                put.addColumn(Bytes.toBytes(e.getKey()), Bytes.toBytes(ee.getKey()), Bytes.toBytes(ee.getValue()));
            }));
            table.put(put);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public Map<String, String> get(String tableName, String rowKey) {
        Map<String, String> map = new HashMap<>();
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);
            for (Cell c : result.rawCells()) {
                map.put(Bytes.toString(CellUtil.cloneQualifier(c)), Bytes.toString(CellUtil.cloneValue(c)));
                log.info("{}={}={}", Bytes.toString(CellUtil.cloneFamily(c)), Bytes.toString(CellUtil.cloneQualifier(c)), Bytes.toString(CellUtil.cloneValue(c)));
            }
            log.info("{}", JSON.toJSONString(map));
        } catch (Exception e) {
            log.error("", e);
        }
        return map;
    }

}