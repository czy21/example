package com.team.portal.configure;

import com.team.application.service.impl.HBaseService;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "com.team")
@Configuration
public class PortalConfiguration {


    @Value("${hbase.zookeeper.quorum}")
    private String zookeeper;

    @Bean
    public HBaseService config() {
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum",zookeeper);
        return new HBaseService(config);
    }

}
