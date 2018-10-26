package com.czy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StartupApplication {

//    protected static final Logger logger = LoggerFactory.getLogger(StartupApplication.class);

    public static void main(String[] args) {
//        logger.info("SpringBoot开始加载");
        SpringApplication.run(StartupApplication.class, args);
//        logger.info("SpringBoot加载完毕");


    }
}
