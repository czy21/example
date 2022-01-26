package com.team.application.model.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {

    private String id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;
}
