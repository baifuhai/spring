package com.test;

import com.test.ioc.bean.Car;
import com.test.ioc.bean.Person;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class TestsIoc {

    @Test
    public void test01() throws Exception {
        //1. 创建 Spring 的 IOC 容器
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans-ioc.xml");

        //2. 从 IOC 容器中获取 bean 的实例
        //Person person = ctx.getBean(Person.class);
        Person person = (Person) ctx.getBean("person");
        Person person2 = (Person) ctx.getBean("person2");
        Car car2 = (Car) ctx.getBean("car2");
        DataSource dataSource = ctx.getBean(DataSource.class);

        //3. 调用方法
        person.hello();
        System.out.println(person);
        System.out.println(person2);
        System.out.println(person.getCar());
        System.out.println(car2);
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection().getClass());

        ctx.close();
    }

}
