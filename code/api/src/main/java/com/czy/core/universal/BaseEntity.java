package com.czy.core.universal;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 实体基类
 * @Author 陈昭宇
 * @Date 2018/7/26
 */
@Data
public class BaseEntity<TEntity> {

    private LocalDateTime AddedTime;
    private LocalDateTime ModifiedTime;
    private String AddedUser;
    private String ModifiedUser;
}
