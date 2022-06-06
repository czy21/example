package com.team.application.service.impl;

import com.team.application.service.HBaseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HBaseServiceImpl implements HBaseService {
//    private Connection connection;
//
//    public HBaseServiceImpl(Configuration config) {
//        try {
//            connection = ConnectionFactory.createConnection(config);
//        } catch (IOException e) {
//            log.error("", e);
//        }
//    }
//
//    @Override
//    public void createNamespace(String namespace) {
//        try (Admin admin = connection.getAdmin()) {
//            NamespaceDescriptor desc = NamespaceDescriptor.create(namespace).build();
//            admin.createNamespace(desc);
//            log.info("namespace {} is create success!", namespace);
//        } catch (IOException e) {
//            log.error("", e);
//        }
//    }
//
//    @Override
//    public void createTable(String tableName, List<String> columnFamily) {
//        try (Admin admin = connection.getAdmin()) {
//            TableName tn = TableName.valueOf(tableName);
//            List<ColumnFamilyDescriptor> cf = columnFamily.stream()
//                    .map(ColumnFamilyDescriptorBuilder::of)
//                    .collect(Collectors.toList());
//            TableDescriptor tableDescriptor = TableDescriptorBuilder
//                    .newBuilder(tn)
//                    .setColumnFamilies(cf)
//                    .build();
//            if (admin.tableExists(tn)) {
//                log.warn("table {} is exists!", tableName);
//            } else {
//                admin.createTable(tableDescriptor);
//                log.info("table {} is create success!", tableName);
//            }
//        } catch (IOException e) {
//            log.error("", e);
//        }
//    }
//
//    @Override
//    public void save(String tableName, String rowKey, Map<String, Map<String, Object>> data) {
//        saveAll(tableName, List.of(MutablePair.of(rowKey, data)));
//    }
//
//    @Override
//    public void saveAll(String tableName, List<MutablePair<String, Map<String, Map<String, Object>>>> datas) {
//        try {
//            TableName tn = TableName.valueOf(tableName);
//            Table table = connection.getTable(tn);
//            List<Put> puts = datas.stream()
//                    .map(t -> {
//                        Put put = new Put(Bytes.toBytes(t.left));
//                        t.right.forEach((fk, fv) ->
//                                fv.forEach((ck, cv) -> put.addColumn(Bytes.toBytes(fk), Bytes.toBytes(ck), Bytes.toBytes(cv.toString())))
//                        );
//                        return put;
//                    }).collect(Collectors.toList());
//            table.put(puts);
//        } catch (Exception e) {
//            log.error("", e);
//        }
//    }
//
//    @Override
//    public int count(String tableName) {
//        int rowCount = 0;
//        try {
//            Table table = connection.getTable(TableName.valueOf(tableName));
//            var scan = new Scan();
//            scan.setFilter(new FirstKeyOnlyFilter());
//            var rs = table.getScanner(scan);
//            for (Result ret : rs) {
//                rowCount += ret.size();
//            }
//        } catch (Exception e) {
//            log.error("", e);
//        }
//        return rowCount;
//    }
//
//    @Override
//    public Map<String, Object> get(String tableName, String rowKey) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            Table table = connection.getTable(TableName.valueOf(tableName));
//            Get get = new Get(Bytes.toBytes(rowKey));
//            Result result = table.get(get);
//            for (Cell c : result.rawCells()) {
//                map.put(Bytes.toString(CellUtil.cloneQualifier(c)), Bytes.toString(CellUtil.cloneValue(c)));
//                log.info("{}={}={}", Bytes.toString(CellUtil.cloneFamily(c)), Bytes.toString(CellUtil.cloneQualifier(c)), Bytes.toString(CellUtil.cloneValue(c)));
//            }
//            log.info("{}", JSON.toJSONString(map));
//        } catch (Exception e) {
//            log.error("", e);
//        }
//        return map;
//    }
}
