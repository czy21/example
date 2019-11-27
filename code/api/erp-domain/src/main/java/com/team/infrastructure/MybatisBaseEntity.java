package com.team.infrastructure;

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

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String createdUser;

    private String modifiedUser;
}
