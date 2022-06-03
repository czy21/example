package com.team.fileresolve.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.db.datasource.RoutingDataSource;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RedisLogReceiver implements StreamListener<String, MapRecord<String, String, String>> {

    public static String KEY = "log-api";
    public static String GROUP = "log-group";

    JdbcTemplate jdbcTemplate;
    StringRedisTemplate redisTemplate;
    ObjectMapper objectMapper;

    public RedisLogReceiver(RoutingDataSource dataSource,
                            StringRedisTemplate redisTemplate,
                            ObjectMapper objectMapper) {
        Map<String, DataSource> dataSourceMap = dataSource.getResolvedDataSources().entrySet().stream().collect(HashMap::new, (m, n) -> m.put(n.getKey().toString(), n.getValue()), Map::putAll);
        jdbcTemplate = new JdbcTemplate(dataSourceMap.get("master"));
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public void onMessage(MapRecord<String, String, String> message) {
//        jdbcTemplate.update("insert into ent_api_log(args) values (?)", new Object[]{objectMapper.writeValueAsString(message.getValue())});
        Map<String,String> msgMap= message.getValue();
        msgMap.put("a","hello");
        System.out.println(msgMap);
        redisTemplate.opsForStream().acknowledge("kf-log-token-group", message);
        redisTemplate.opsForStream().delete(message);
    }
}
