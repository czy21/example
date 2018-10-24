package com.czy.entity.vo;

import lombok.Data;

@Data
public class UserDto {
    private String UserId;
    private String LoginName;
    private String UserName;
    private String Email;
    private String Phone;
    private Boolean IsHeader;
    private String DepartmentId;
}
