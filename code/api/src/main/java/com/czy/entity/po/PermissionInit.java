package com.czy.entity.po;

import com.czy.core.universal.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 
 *
 * @author 陈昭宇
 * @since 2018-10-20
 */
public class PermissionInit extends BaseEntity<PermissionInit> {

    @TableId
    private String PermissionId;
    /**
     * 程序对用url地址
     */
    private String Url;
    /**
     * 对应shiro权限
     */
    private String PermissionInit;
    /**
     * 排序
     */
    private Integer Sort;


    public String getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(String PermissionId) {
        this.PermissionId = PermissionId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getPermissionInit() {
        return PermissionInit;
    }

    public void setPermissionInit(String PermissionInit) {
        this.PermissionInit = PermissionInit;
    }

    public Integer getSort() {
        return Sort;
    }

    public void setSort(Integer Sort) {
        this.Sort = Sort;
    }

    @Override
    public String toString() {
        return "PermissionInit{" +
                ", PermissionId=" + PermissionId +
                ", Url=" + Url +
                ", PermissionInit=" + PermissionInit +
                ", Sort=" + Sort +
                "}";
    }
}
