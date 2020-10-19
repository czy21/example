package com.team.application.model.dto;

import com.team.application.model.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {
    private String account;
    private String password;
    private String userName;
    private String email;
}
