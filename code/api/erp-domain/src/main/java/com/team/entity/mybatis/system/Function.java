package com.team.entity.mybatis.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 陈昭宇
 * @description 权限表
 * @date 2019-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Function extends BaseEntity<Function> {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long functionId;
    /**
     * 权限值
     */
    private String functionCode;
    /**
     * 权限名称
     */
    private String functionName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
}
