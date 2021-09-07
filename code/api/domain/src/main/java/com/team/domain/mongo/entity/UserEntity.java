package com.team.domain.mongo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "ent_institution")
public class UserEntity extends ExtensionModel {
    private String id;
    private String name;
    private String tenantId;
}
