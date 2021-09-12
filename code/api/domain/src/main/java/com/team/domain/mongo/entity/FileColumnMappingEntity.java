package com.team.domain.mongo.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "met_file_column_mapping")
public class FileColumnMappingEntity {
    private String id;
    private String tableName;
    private String businessType;
    private List<Field> fields;


    @Data
    public static class Field {
        private String key;
        private String column;
        private String header;
        private List<Map<String, Object>> validators;
    }
}
