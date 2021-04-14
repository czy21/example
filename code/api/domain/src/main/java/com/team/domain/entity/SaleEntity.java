package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.team.infrastructure.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@TableName(value = "ent_sfl_inspect_sale")
public class SaleEntity extends BaseEntity {
    private String fromInstitutionCode;
    private String fromInstitutionName;
    private String fromInstitutionCodeFormat;
    private String toInstitutionCode;
    private String toInstitutionName;
    private String toInstitutionNameFormat;
    private String productCode;
    private String productName;
    private String productSpec;
    private String productCodeFormat;
    private String productUnit;
    private String productUnitFormat;

}
