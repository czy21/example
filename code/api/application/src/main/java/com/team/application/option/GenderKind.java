package com.team.application.option;

import com.team.cooperated.annotation.Description;
import com.team.cooperated.annotation.OptionName;

@OptionName(value = "gender")
public enum GenderKind {
    @Description(label = "男")
    MALE,
    @Description(label = "女")
    FEMALE;


}
