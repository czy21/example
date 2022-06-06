package com.team.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.infrastructure.metadata.EntityMetadataHandler;
import com.team.infrastructure.oss.OSSConfigure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@Import({
        OSSConfigure.class
})
class InfrastructureConfigure {

    @Bean
    public EntityMetadataHandler entityMetadataHandler() {
        return new EntityMetadataHandler();
    }

    @Bean
    public AuditorAware<LocalDateTime> JPAEntityAuditor() {
        return () -> Optional.of(LocalDateTime.now());
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        return template;
    }
}

