package com.team.domain.configuration;

import com.team.domain.node.Actor;
import com.team.domain.node.ActorRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EntityScan(basePackageClasses = {Actor.class})
@EnableNeo4jRepositories(basePackageClasses = {ActorRepository.class})
public class DomainConfigurationTest {

}
