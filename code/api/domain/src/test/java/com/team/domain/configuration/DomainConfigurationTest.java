package com.team.domain.configuration;

import com.team.domain.node.Actor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = {Actor.class})
public class DomainConfigurationTest {

}
