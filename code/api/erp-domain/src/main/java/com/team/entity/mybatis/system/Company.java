package com.team.entity.mybatis.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 陈昭宇
 * @description 公司表
 * @date 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity<Company> {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 公司地点
     */
    private String location;
    /**
     * 公司电话
     */
    private String phone;
    /**
     * 公司传真
     */
    private String fax;
    /**
     * 公司邮编
     */
    private String postcode;
    /**
     * 备注
     */
    private String remark;
    private Boolean enabled;
}
