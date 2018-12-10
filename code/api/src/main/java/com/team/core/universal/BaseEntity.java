package com.team.core.universal;

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
public class BaseEntity<TEntity> {

    @TableField("added_time")
    private LocalDateTime addedTime;
    @TableField("modified_time")
    private LocalDateTime modifiedTime;
    @TableField("added_user")
    private String addedUser;
    @TableField("modified_user")
    private String modifiedUser;
}
