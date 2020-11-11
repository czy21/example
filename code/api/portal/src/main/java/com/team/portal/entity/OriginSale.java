package com.team.portal.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OriginSale {
    private String dataId;
    private String fileId;
    private String institutionCode;
    private String institutionName;
    private LocalDate saleDate;
    private String customerCode;
    private String customerName;
    private String productCode;
    private String productName;
    private String productSpec;
    private String productBatch;
    private String validDate;
    private Integer quantity;
    private String productUnit;
    private BigDecimal price;
    private BigDecimal amount;
    private String producer;
    private String tenantId;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private Integer isDeleted;

    private String behavior;
    private String saleId;
    private String orderDate;
    private String customerAddr;
    private String generalName;
    private String productDate;
    private String productModel;
    private String productLine;
    private String saleOrder;
    private Integer rowNum;
    private String despatchOrder;
    private String companyName;
    private String wareHouse;
    private String vendorName;
    private String department;
    private String invoiceDate;
    private String taxAmount;
}