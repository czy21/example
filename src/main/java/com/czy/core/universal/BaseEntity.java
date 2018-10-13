package com.czy.core.universal;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * @Description 实体基类
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
public class BaseEntity<TEntity extends Model> extends Model<TEntity> {

    private Long Id;
    private LocalDateTime AddedTime;
    private LocalDateTime ModifiedTime;

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    @Override
    protected Serializable pkVal() {
        return this.Id;
    }
}
