package com.team.application;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class ApplicationConfig {

//    @Bean
//    public HBaseService hbaseService() {
//        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
//        config.set("hbase.zookeeper.quorum",zookeeper);
//        return new HBaseServiceImpl(config);
//    }

}
