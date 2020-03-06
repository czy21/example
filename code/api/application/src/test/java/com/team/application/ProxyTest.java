package com.team.application;


import com.team.application.proxy.Car;
import com.team.application.proxy.CarImpl;
import com.team.application.proxy.CarProxy;

public class ProxyTest {


    public static void main(String[] args) {
        CarProxy carProxy = new CarProxy(new CarImpl());
        Car car = carProxy.getTarget();
        car.buy("jjj");

        System.out.println("aa");

    }

}
