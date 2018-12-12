package com.team.extension;

public class StringExtension {

    public static String GuidEmpty = "00000000-0000-0000-0000-000000000000";

    public static Boolean guidIsEmpty(String str) {
        return str.equals(GuidEmpty);
    }

}
