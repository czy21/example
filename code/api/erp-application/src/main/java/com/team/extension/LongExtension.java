package com.team.extension;

public class LongExtension {

    public static Long empty = 0L;

    public static Boolean guidIsEmpty(Long val) {
        return val.equals(empty) || val == null;
    }

}
