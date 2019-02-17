package com.team.entity.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String loginName;
    private String userName;
    private String email;
    private String phone;
    private Boolean isHeader;
    private Long departmentId;
    private Boolean enabled;
}
