package com.team.infrastructure.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @TableId
    private String id;

    @TableField(fill = FieldFill.INSERT)
    @CreatedDate
    private LocalDateTime createdDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private String createdUser;

    private String modifiedUser;
}
