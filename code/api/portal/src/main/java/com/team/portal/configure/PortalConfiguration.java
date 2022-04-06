package com.team.portal.configure;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = {"com.team","com.czy"})
@Configuration
public class PortalConfiguration {

}
