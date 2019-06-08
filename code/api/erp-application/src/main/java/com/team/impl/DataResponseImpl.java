package com.team.impl;

import com.team.provider.DataResponse;

import java.util.Map;


public class DataResponseImpl implements DataResponse {

    private String key;

    public DataResponseImpl(String key) {
        this.key = key;
    }

    @Override
    public void excute(Map<String, Object> map) {


        map.put(key, "aaa");
    }
}
