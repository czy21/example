package com.team.application.model;

import com.team.application.kind.SqlKind;
import com.team.domain.mongo.entity.FileColumnMappingEntity;
import lombok.Data;

import java.util.Map;

@Data
public class RowModel {
    private String tableName;
    private String businessType;
    private SqlKind sqlKind;
    private String fileId;
    private Integer index;
    private Map<String, RowModel.ColModel> data;

    @Data
    public static class ColModel {
        private String key;
        private String column;
        private String header;
        private Object value;
        private Integer index;
    }
}
