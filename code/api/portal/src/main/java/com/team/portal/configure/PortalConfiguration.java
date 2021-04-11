package com.team.portal.configure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(value = "com.team")
@Configuration
@EnableScheduling
public class PortalConfiguration {

}
