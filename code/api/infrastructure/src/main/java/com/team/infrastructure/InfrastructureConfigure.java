package com.team.infrastructure;

import com.team.infrastructure.metadata.EntityMetadataHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfigure {
    @Bean
    @ConditionalOnMissingBean
    public EntityMetadataHandler entityMetadataHandler() {
        return new EntityMetadataHandler();
    }
}
