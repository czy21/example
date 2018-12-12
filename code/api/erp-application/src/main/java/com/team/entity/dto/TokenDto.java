package com.team.entity.dto;

import com.team.extension.entity.MenuExtension;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
    private List<MenuExtension.Node> menus;
    private AccountDto user;
    private String value;
}
