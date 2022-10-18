package com.team.portal;


import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.team"})
@SpringBootApplication
public class PortalApplication {
    public static void main(String[] args) throws Exception {
        new WebdavServlet().init();
        SpringApplication app = new SpringApplication(PortalApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }
}
