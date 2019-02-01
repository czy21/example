package com.team.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陈昭宇
 * @description 实体基类
 * @date 2018/7/26
 */
@Data
public class BaseEntity<TEntity> {

    private LocalDateTime addedTime;

    private LocalDateTime modifiedTime;

    private String addedUser;

    private String modifiedUser;
}
