package com.team.entity.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {

    private String id;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String createdUser;

    private String modifiedUser;
}
