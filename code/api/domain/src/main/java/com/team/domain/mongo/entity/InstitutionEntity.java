package com.team.domain.mongo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "ent_institution")
public class InstitutionEntity extends ExtensionModel {
    private String id;
    private String name;
    @Field("tenant_id")
    private String tenantId;
}
