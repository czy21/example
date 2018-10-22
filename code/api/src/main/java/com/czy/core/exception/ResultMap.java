package com.czy.core.exception;

import com.alibaba.fastjson.JSONObject;

import java.io.PrintWriter;
import java.util.HashMap;

public class ResultMap {

    public static Boolean ResponseException(PrintWriter printWriter, Integer status, String message) {

        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("message", message);
        printWriter.append(json.toString());
        return false;
    }

}
