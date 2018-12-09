package com.team.core.universal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description 实体基类
 * @author 陈昭宇
 * @date 2018/7/26
 */
@Data
public class BaseEntity {

    @TableField("added_time")
    private LocalDateTime addedTime;
    @TableField("modified_time")
    private LocalDateTime modifiedTime;
    @TableField("added_user")
    private String addedUser;
    @TableField("modified_user")
    private String modifiedUser;
}
