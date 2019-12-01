package com.team.application.pocket;

import com.team.cooperated.annotation.Description;
import com.team.cooperated.annotation.PocketName;

@PocketName(value = "gender")
public enum EnumGender {
    @Description(label = "男")
    MALE,
    @Description(label = "女")
    FEMALE
}
