package com.team.core.extension;

import java.util.List;

public class CollectionExtension {
    public static Boolean ListIsNullOrEmpty(List arr) {
        return arr == null || arr.size() == 0;
    }
}
