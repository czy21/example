package com.team.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyEntity extends MybatisBaseEntity {
    /**
     * 公司名称
     */
    private String name;

    private String remark;
}