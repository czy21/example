package com.team.domain.model;

import com.team.infrastructure.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ent_sys_department")
@Entity
public class DepartmentEntity extends BaseEntity {
    /**
     * 上级部门
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String name;

    @ManyToOne
    private CompanyEntity company;
}