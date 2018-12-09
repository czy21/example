package com.team.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.team.core.universal.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 公司表
 *
 * @author 陈昭宇
 * @date 2018-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId("company_id")
    private String companyId;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 联系人
     */
    @TableField("contact_person")
    private String contactPerson;
    /**
     * 公司地点
     */
    @TableField("location")
    private String location;
    /**
     * 公司电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 公司传真
     */
    @TableField("fax")
    private String fax;
    /**
     * 公司邮编
     */
    @TableField("postcode")
    private String postcode;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    @TableField("enabled")
    private Boolean enabled;
}
