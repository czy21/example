package com.team.fileresolve.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.domain.log.ApiLogModel;
import com.team.infrastructure.datasource.RoutingDataSource;
import lombok.SneakyThrows;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisReceiver implements StreamListener<String, MapRecord<String, String, String>> {

    JdbcTemplate jdbcTemplate;
    StringRedisTemplate redisTemplate;
    ObjectMapper objectMapper;

    public RedisReceiver(RoutingDataSource dataSource,
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
        jdbcTemplate.update("insert into ent_api_log(args) values (?)", new Object[]{objectMapper.writeValueAsString(message.getValue())});
        redisTemplate.opsForStream().delete(message);
    }
}
