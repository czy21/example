package com.team.core.extension;

import java.util.ArrayList;
import java.util.List;

public class StringExtension {

    public static List<String> ConvertAllToLower(List<String> list) {
        List<String> temp = new ArrayList<>();
        list.forEach(t -> temp.add(t.toLowerCase()));
        return temp;
    }

}
