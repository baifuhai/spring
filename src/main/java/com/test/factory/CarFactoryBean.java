package com.test.factory;

import com.test.ioc.bean.Car;
import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Car getObject() throws Exception {
        return new Car(name);
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
