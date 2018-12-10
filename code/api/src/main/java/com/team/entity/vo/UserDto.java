package com.team.entity.vo;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String loginName;
    private String userName;
    private String email;
    private String phone;
    private Boolean isHeader;
    private String departmentId;
    private Boolean enabled;
}
