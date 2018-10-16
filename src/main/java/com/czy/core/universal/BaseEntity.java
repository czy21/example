package com.czy.core.universal;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * @Description 实体基类
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
public class BaseEntity<TEntity extends Model> extends Model<TEntity> {

    private LocalDateTime AddedTime;
    private LocalDateTime ModifiedTime;
    private String AddedUser;
    private String ModifiedUser;

    public LocalDateTime getAddedTime() {
        return AddedTime;
    }

    public void setAddedTime(LocalDateTime addedTime) {
        AddedTime = addedTime;
    }

    public LocalDateTime getModifiedTime() {
        return ModifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        ModifiedTime = modifiedTime;
    }

    public String getAddedUser() {
        return AddedUser;
    }

    public void setAddedUser(String addedUser) {
        AddedUser = addedUser;
    }

    public String getModifiedUser() {
        return ModifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        ModifiedUser = modifiedUser;
    }
}
