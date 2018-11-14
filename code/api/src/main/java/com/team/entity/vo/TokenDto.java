package com.team.entity.vo;

import com.team.entity.vo.LoginDto;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
    private List<MenuDto> menus;
    private LoginDto user;
    private String value;
}
