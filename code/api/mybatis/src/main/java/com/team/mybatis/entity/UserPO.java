package com.team.mybatis.entity;

import com.team.mybatis.annotation.SafeField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPO {
    private String id;
    private String name;

    @SafeField
    private String phoneNo;
    private String idNum;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createUser;
    private String updateUser;
    private Boolean deleted;
}
