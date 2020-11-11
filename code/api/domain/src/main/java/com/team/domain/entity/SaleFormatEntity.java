package com.team.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "ent_sfl_sale_format")
public class SaleFormatEntity {

    private String id;
    private String institutionId;
    private String fromInstitutionId;
    private String fromInstitutionName;
    private String fromInstitutionIdFormat;
    private String fromInstitutionNameFormat;
    private String toInstitutionId;
    private String toInstitutionName;
    private String toInstitutionIdFormat;
    private String toInstitutionNameFormat;
    private String productId;
    private String productIdFormat;
    private String productName;
    private String productSpecs;
    private String productBatchCode;
    private String productCount;
    private String unit;
    private String price;
    private String amount;


}
