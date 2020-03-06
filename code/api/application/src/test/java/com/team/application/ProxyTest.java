package com.team.application;


import com.team.application.proxy.Car;
import com.team.application.proxy.CarImpl;
import com.team.application.proxy.CglibProxy;
import com.team.application.proxy.DynamicProxy;

public class ProxyTest {


    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(new CarImpl());
        Car car = dynamicProxy.getTarget();
        car.buy("jjj");

        Car car1 = CglibProxy.getInstance().getProxy(CarImpl.class);
        car1.buy("a");

        System.out.println("aa");
    }

}
