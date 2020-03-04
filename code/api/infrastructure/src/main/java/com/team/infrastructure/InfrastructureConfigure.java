package com.team.infrastructure;

import com.team.infrastructure.metadata.EntityMetadataHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
class InfrastructureConfigure {
    @Bean
    @ConditionalOnMissingBean
    public EntityMetadataHandler entityMetadataHandler() {
        return new EntityMetadataHandler();
    }


}

@Configuration
class EntityAuditing implements AuditorAware<LocalDateTime> {
    @Override
    public Optional<LocalDateTime> getCurrentAuditor() {
        return Optional.of(LocalDateTime.now());
    }

}