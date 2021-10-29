package com.team.domain.entity;

import com.team.infrastructure.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MaterialEntity extends BaseEntity {
    private String name;
    private String path;
    private String kind;
    private MaterialTargetEntity materialTarget;
}
