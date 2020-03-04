package com.team.domain.model;

import com.team.infrastructure.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ent_sys_company")
public class CompanyEntity extends BaseEntity {
    private String name;

}
