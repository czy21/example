package com.team.domain.infrastructure.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陈昭宇
 * @description 实体基类
 * @date 2018/7/26
 */
@Data
public class MybatisBaseEntity {

    @TableId
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedDate;

    private String createdUser;

    private String modifiedUser;
}
