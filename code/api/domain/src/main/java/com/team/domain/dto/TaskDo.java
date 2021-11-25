package com.team.domain.dto;


import com.team.infrastructure.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskDo extends BaseEntity {
    private String code;
    private String batchId;
    private String status;
    private int executeCount;
    private int finishedCount;
}
