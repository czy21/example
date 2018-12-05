package com.team.entity.vo;

import com.team.core.extension.entity.MenuExtension;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
    private List<MenuExtension.Node> menus;
    private AccountDto user;
    private String value;
}
