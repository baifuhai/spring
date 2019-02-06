package com.test.factory;

import com.test.ioc.bean.Car;

/**
 * 实例工厂方法
 */
public class InstanceCarFactory {

    public Car getInstance(String name) {
        return new Car(name);
    }

}
