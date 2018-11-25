package com.czy.core;

import com.team.core.constant.ProjectConstant;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;

public class GetControllersTest {

    public static void main(String[] args) {
        getClasses();
    }
    public static Object getClasses() {
        Package[] pak = Package.getPackages();
        return pak;
    }
}
