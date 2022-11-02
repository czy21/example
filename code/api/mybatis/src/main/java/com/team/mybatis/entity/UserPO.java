package com.team.mybatis.entity;


import com.meditrusthealth.mth.common.security.annotation.SensitiveField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPO {
    private Long id;
    @SensitiveField
    private String name;
    @SensitiveField
    private String phoneNo;
    @SensitiveField
    private String idNum;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String createUser;
    private String updateUser;
    private Boolean deleted;
}
