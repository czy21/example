package com.team.fileresolve.receiver;

import com.team.infrastructure.datasource.RoutingDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisMessageConsumer implements InitializingBean, ApplicationContextAware {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() {
        new Thread(() -> {
            while (true) {
                var msg = stringRedisTemplate.opsForList().rightPop("kf:log:token2", 1000L, TimeUnit.SECONDS);
                jdbcTemplate.update("insert into ent_api_log(args) values (?)", new Object[]{msg});
            }
        }).start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RoutingDataSource dataSource = applicationContext.getBean(RoutingDataSource.class);
        Map<String, DataSource> dataSourceMap = dataSource.getResolvedDataSources().entrySet().stream().collect(HashMap::new, (m, n) -> m.put(n.getKey().toString(), n.getValue()), Map::putAll);
        jdbcTemplate = new JdbcTemplate(dataSourceMap.get("master"));
    }
}
