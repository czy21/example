package com.team.domain.mongo.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "met_table")
public class TableMetadataEntity {
    private String id;
    private String name;
    private List<ColumnMetadata> columns;

    @Data
    public static class ColumnMetadata {
        private String name;
        private String type;
        private String desc;
    }
}
