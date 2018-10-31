package com.team.entity.po;

import com.team.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 公司表
 *
 * @author 陈昭宇
 * @since 2018-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity<Company> {
    @TableId
    private String CompanyId;
    private String CompanyName;
    private String ContactPerson;
    private String Location;
    private String Phone;
    private String Fax;
    private String Postcode;
    private String Comment;
    private Boolean Enabled;
}
