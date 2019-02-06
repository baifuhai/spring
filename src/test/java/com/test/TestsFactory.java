package com.test;

import com.test.ioc.bean.Car;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestsFactory {

    @Test
    public void test01() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factory.xml");

        Car car = (Car) ctx.getBean("car");
        Car car2 = (Car) ctx.getBean("car2");
        Car car3 = (Car) ctx.getBean("car3");

        System.out.println(car);
        System.out.println(car2);
        System.out.println(car3);

        ctx.close();
    }

}
