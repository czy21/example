package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_task")
@NoArgsConstructor
public class TaskEntity extends BaseEntity {
    private String code;
    private String batchId;
    private String status;
    private int executeCount;

    public TaskEntity(String code, String batchId) {
        this.code = code;
        this.batchId = batchId;
    }

    public static TaskEntity of(String code, String batchId) {
        return new TaskEntity(code, batchId);
    }
}
