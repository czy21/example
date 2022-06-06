package com.team.domain.mongo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "met_table_extension")
public class TableExtensionEntity extends TableMetadataEntity {
    private String id;
    private String tenantId;
}
