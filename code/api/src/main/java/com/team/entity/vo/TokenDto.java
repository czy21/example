package com.team.entity.vo;

import com.team.core.util.TreeUtil;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
    private List<TreeUtil.Node> menus;
    private AccountDto user;
    private String value;
}
