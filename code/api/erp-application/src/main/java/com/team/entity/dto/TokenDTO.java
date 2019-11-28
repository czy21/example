package com.team.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class TokenDTO {
    private List<String> permissions;
    private AccountDTO user;
    private String value;
}
