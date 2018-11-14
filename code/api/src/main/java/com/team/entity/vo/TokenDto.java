package com.team.entity.vo;

import com.team.core.util.TreeUtil;
import com.team.entity.vo.LoginDto;
import lombok.Data;

import java.util.List;

@Data
public class TokenDto {
    private List<TreeUtil.Node> menus;
    private LoginDto user;
    private String value;
}
