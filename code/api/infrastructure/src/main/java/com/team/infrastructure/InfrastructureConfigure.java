package com.team.infrastructure;

import com.team.infrastructure.metadata.EntityMetadataHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
class InfrastructureConfigure {
    @Bean
    @ConditionalOnMissingBean
    public EntityMetadataHandler entityMetadataHandler() {
        return new EntityMetadataHandler();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Component
    public static class EntityAuditing implements AuditorAware<LocalDateTime> {
        @Override
        public Optional<LocalDateTime> getCurrentAuditor() {
            return Optional.of(LocalDateTime.now());
        }

    }

}

