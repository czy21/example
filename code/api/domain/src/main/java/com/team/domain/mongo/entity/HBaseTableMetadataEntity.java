package com.team.domain.mongo.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "met_hbase_table")
public class HBaseTableMetadataEntity {
    private String id;
    private String namespace;
    private String tableName;
    private Map<String, List<String>> columnFamily;
}
