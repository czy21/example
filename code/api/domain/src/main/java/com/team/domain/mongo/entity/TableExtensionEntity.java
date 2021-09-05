package com.team.domain.mongo.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "met_table_extension")
public class TableExtensionEntity {
    private String id;
    private String tableName;
    private List<ColumnMetadata> columns;
    private String tenantId;

    @Data
    public static class ColumnMetadata {
        private String name;
        private String type;
        private String desc;
    }
}
