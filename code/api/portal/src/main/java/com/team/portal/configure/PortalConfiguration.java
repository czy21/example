package com.team.portal.configure;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@ConditionalOnExpression(value = "'${spring.redis.cluster.nodes}'!=null")
@Configuration
public class PortalConfiguration implements InitializingBean {

    StringRedisTemplate redisTemplate;
    @Value("#{'${spring.redis.cluster.nodes}' != null}")
    boolean isCluster;

    public PortalConfiguration(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
