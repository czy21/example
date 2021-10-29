package com.team.infrastructure.datasource;

import com.team.infrastructure.annotation.DS;
import lombok.SneakyThrows;

public class DynamicDataSourceContext implements AutoCloseable {
    private static final ThreadLocal<String> datasourceKey = new ThreadLocal<>();

    @SneakyThrows
    public static String getDataSourceKey() {
        String key = datasourceKey.get();
        return key == null ? (String) DS.class.getMethod("value").getDefaultValue() : key;
    }

    public static void put(String key) {
        datasourceKey.set(key);
    }

    @Override
    public void close() {
        datasourceKey.remove();
    }
}
