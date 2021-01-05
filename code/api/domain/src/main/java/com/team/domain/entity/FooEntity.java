package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "foo")
public class FooEntity extends BaseEntity {
    private String number;
}