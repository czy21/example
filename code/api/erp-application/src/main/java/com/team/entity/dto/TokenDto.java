package com.team.entity.dto;

import com.team.extension.MenuExtension;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
    private List<MenuExtension.Node> menus;
    private List<String> permissions;
    private AccountDto user;
    private String value;
}
