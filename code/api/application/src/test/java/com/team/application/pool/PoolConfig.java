package com.team.application.pool;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class PoolConfig {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ProxyFactoryBean counter() {
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setTargetSource(counterPoolTargetSource());
        return pfb;
    }

    @Bean
    public CommonsPool2TargetSource counterPoolTargetSource() {
        CommonsPool2TargetSource pool = new CommonsPool2TargetSource();
        pool.setMaxSize(3);
        pool.setTargetBeanName("counterTarget");
        return pool;
    }

    @Bean(name = "counterTarget")
    @Scope(value = "prototype")
    public Counter counterTarget() {
        return new CounterImpl();
    }
}
