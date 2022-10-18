package com.team.webdav.config;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebdavConfig {

    @Autowired
    WebDavProperties webDavProperties;

    @Bean
    ServletRegistrationBean<WebdavServlet> webdavServletServletRegistrationBean() {
        ServletRegistrationBean<WebdavServlet> registrationBean = new ServletRegistrationBean<>(new WebdavServlet(), "/dav/*");
        registrationBean.addInitParameter("listings", "true");
        registrationBean.addInitParameter("readonly", "false");
        registrationBean.addInitParameter("debug", "0");
        return registrationBean;
    }

    @Bean
    TomcatContextCustomizer tomcatContextCustomizer() {
        return t -> {
            t.setDocBase(webDavProperties.getDataDir());
        };
    }
}
