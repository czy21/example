package com.team.fileresolve.receiver;

import com.team.infrastructure.datasource.RoutingDataSource;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisReceiver implements StreamListener<String, ObjectRecord<String, Object>> {

    JdbcTemplate jdbcTemplate;
    StringRedisTemplate redisTemplate;

    public RedisReceiver(RoutingDataSource dataSource,
                         StringRedisTemplate redisTemplate) {
        Map<String, DataSource> dataSourceMap = dataSource.getResolvedDataSources().entrySet().stream().collect(HashMap::new, (m, n) -> m.put(n.getKey().toString(), n.getValue()), Map::putAll);
        jdbcTemplate = new JdbcTemplate(dataSourceMap.get("master"));
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(ObjectRecord<String, Object> message) {
        System.out.println(message);
        jdbcTemplate.update("insert into ent_api_log(args) values (?)", new Object[]{message.getValue()});
        redisTemplate.opsForStream().delete(message);
    }
}
