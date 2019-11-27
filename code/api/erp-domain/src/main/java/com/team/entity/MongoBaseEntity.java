package com.team.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MongoBaseEntity {
    @Id
    private String id;
}
