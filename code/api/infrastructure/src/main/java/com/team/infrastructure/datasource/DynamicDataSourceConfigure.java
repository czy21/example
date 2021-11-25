package com.team.infrastructure.datasource;

import com.team.infrastructure.annotation.DS;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DynamicDataSourceConfigure {
    DynamicDataSourceProperties dynamicDataSourceProperties;
    Map<String, DataSource> dsMap;

    public DynamicDataSourceConfigure(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
        dsMap = dynamicDataSourceProperties.getDatasource()
                .entrySet()
                .stream()
                .map(t -> {
                    t.getValue().setPoolName(String.join(" ", new String[]{"datasource", "=>", t.getKey()}));
                    var ds = new HikariDataSource(t.getValue());
                    return Map.of(t.getKey(), ds);
                })
                .collect(HashMap::new, Map::putAll, Map::putAll);
    }

    @SuppressWarnings("unchecked")
    @Bean
    @Primary
    public DataSource primaryDataSource() throws NoSuchMethodException {
        RoutingDataSource rds = new RoutingDataSource();
        rds.setTargetDataSources((Map) dsMap);
        String master = (String) DS.class.getDeclaredMethod("value").getDefaultValue();
        if (!dsMap.containsKey(master)) {
            log.warn("master datasource is null");
        }
        rds.setDefaultTargetDataSource(dsMap.get(master));
        return rds;
    }

}
