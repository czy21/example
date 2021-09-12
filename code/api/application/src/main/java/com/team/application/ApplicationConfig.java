package com.team.application;

import com.team.application.service.HBaseService;
import com.team.application.service.impl.HBaseServiceImpl;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class ApplicationConfig {

    @Value("${hbase.zookeeper.quorum}")
    private String zookeeper;

    @Bean
    public HBaseService hbaseService() {
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum",zookeeper);
        return new HBaseServiceImpl(config);
    }
}
