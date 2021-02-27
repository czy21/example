package com.team.application.provider.service.impl;

import com.team.application.provider.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

@DubboService(version = "1.0.0",interfaceName = "erp.DemoService")
public class DemoServiceImpl implements DemoService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String helloWorld(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
