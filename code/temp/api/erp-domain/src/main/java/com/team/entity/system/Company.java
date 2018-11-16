package com.team.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 公司表
 *
 * @author 陈昭宇
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity<Company> {
    @TableId
    private String CompanyId;
    /**
     * 公司名称
     */
    private String CompanyName;
    /**
     * 联系人
     */
    private String ContactPerson;
    /**
     * 公司地点
     */
    private String Location;
    /**
     * 公司电话
     */
    private String Phone;
    /**
     * 公司传真
     */
    private String Fax;
    /**
     * 公司邮编
     */
    private String Postcode;
    /**
     * 备注
     */
    private String Remark;
    private Boolean Enabled;
}
