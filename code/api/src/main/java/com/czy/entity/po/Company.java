package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


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
