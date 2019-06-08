package com.team.provider;

import java.util.Map;

public interface ResponseProvider {

    void add(Map<String, DataResponse> map);

    Map<String, DataResponse> read();

}
