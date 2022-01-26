package com.team.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @TableId
    @Id
    private String id;

    @TableField(fill = FieldFill.INSERT)
    @CreatedDate
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @LastModifiedDate
    private LocalDateTime updateTime;

    private String createUser;

    private String UpdateUser;
}
