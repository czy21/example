package com.team.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultResponseProvider implements ResponseProvider, ApplicationContextAware {

    private Map<String, DataResponse> map = new HashMap<>();


    @Override
    public void add(Map<String, DataResponse> val) {
        this.map.putAll(val);
    }

    @Override
    public Map<String, DataResponse> read() {
        return map;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map= applicationContext.getBeansOfType(DataResponse.class);
    }
}
