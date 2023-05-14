package com.team.application.service.impl;

import com.team.application.service.DictService;
import org.springframework.stereotype.Component;

@Component
public class DictServiceImpl implements DictService {

    @Override
    public String findSimpleLabel(String code, Object value) {
        return "aaaa";
    }
}
