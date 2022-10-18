package com.team.webdav;


import com.team.webdav.config.WebdavSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan(basePackageClasses = WebdavSupport.class)
@ComponentScan(value = {"com.team"})
@SpringBootApplication
public class WebDavApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebDavApplication.class);
        app.setAllowCircularReferences(true);
        app.run(args);
    }

    @Bean
    public TomcatContextCustomizer tomcatContextCustomizer() {
        return t -> {
            t.setDocBase("D:/dav");
        };
    }

}
