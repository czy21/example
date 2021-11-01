package com.team.application.option;

import com.team.cooperated.annotation.OptionName;
import com.team.cooperated.option.Option;

@OptionName(value = "yesNo")
public enum YesNoKind implements Option<Integer> {
    YES(1, "是"),
    NO(0, "否");

    Integer value;
    String label;

    YesNoKind(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
