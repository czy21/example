package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "ent_migrate_performance_log")
public class MigratePerformanceLogEntity extends BaseEntity {
    private String target;
    private int tableCount;
    private int batchCount;
    private String batchId;
    private String durationUnit;
    private BigDecimal duration;
    private int sequence;
}
