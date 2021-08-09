package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_migrate_performance_log")
public class MigratePerformanceLogEntity extends BaseEntity {
    private String target;
    private int count;
    private String durationUnit;
    private BigDecimal duration;
    private int sequence;
    private String batchId;
}
