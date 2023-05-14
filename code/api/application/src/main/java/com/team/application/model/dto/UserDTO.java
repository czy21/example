package com.team.application.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.team.application.model.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {
    private String account;
    private String userName;
    private String email;
    private Integer status;
    private String statusName;
}
