package com.team.entity.vo;

import com.team.entity.vo.LoginDto;
import lombok.Data;

@Data
public class TokenDto {

    public TokenDto(LoginDto user, String value) {
        User = user;
        Value = value;
    }

    private LoginDto User;
    private String Value;
}
