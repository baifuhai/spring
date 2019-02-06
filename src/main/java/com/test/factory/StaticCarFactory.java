package com.test.factory;

import com.test.ioc.bean.Car;

/**
 * 静态工厂方法
 */
public class StaticCarFactory {

    public static Car getInstance(String name) {
        return new Car(name);
    }

}
