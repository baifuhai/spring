package com.test;

import com.test.aop.Calculator;
import com.test.aop.CalculatorImpl;
import com.test.aop.CalculatorLoggingImpl;
import com.test.aop.CalculatorLoggingProxy;
import com.test.aop.CalculatorLoggingProxy2;
import com.test.aop.CalculatorTimingProxy;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class TestsAop {

    @Test
    public void test01() {
        Calculator calculator = new CalculatorLoggingImpl();

        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.div(1, 2));
    }

    @Test
    public void test02() {
        Calculator target = new CalculatorImpl();

        Calculator proxy = new CalculatorLoggingProxy(target).getProxy();
        Calculator proxy2 = CalculatorLoggingProxy2.getProxy(target);

        System.out.println(proxy.add(1, 2));
        System.out.println(proxy.div(1, 2));

        System.out.println("===============================");

        System.out.println(proxy2.add(1, 2));
        System.out.println(proxy2.div(1, 2));

        System.out.println("===============================");

        System.out.println(proxy.getClass());
        System.out.println(proxy2.getClass());
        System.out.println(Arrays.asList(proxy.getClass().getInterfaces()));
        System.out.println(Arrays.asList(proxy2.getClass().getInterfaces()));
    }

    @Test
    public void test03() {
        Calculator target = new CalculatorImpl();

        Calculator proxy = CalculatorLoggingProxy2.getProxy(CalculatorTimingProxy.getProxy(target));

        System.out.println(proxy.add(1, 2));
        System.out.println(proxy.div(1, 2));
    }

    @Test
    public void testSpring() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans-aop.xml");

        Calculator calculator = ctx.getBean(Calculator.class);

        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.div(1, 2));

        System.out.println(calculator.getClass());
        System.out.println(Arrays.asList(calculator.getClass().getInterfaces()));

        //proxy-target-class="true"（CalculatorImpl子类，可以转换成CalculatorImpl）
        //class com.test.aop.CalculatorImpl$$EnhancerBySpringCGLIB$$1881c048
        //[
        // interface org.springframework.aop.SpringProxy,
        // interface org.springframework.aop.framework.Advised,
        // interface org.springframework.cglib.proxy.Factory
        //]

        //proxy-target-class="false"（Calculator实现类，不可以转换成CalculatorImpl）
        //class com.sun.proxy.$Proxy7
        //[
        // interface com.test.aop.Calculator,
        // interface org.springframework.aop.SpringProxy,
        // interface org.springframework.aop.framework.Advised,
        // interface org.springframework.core.DecoratingProxy
        //]

        CalculatorImpl impl = (CalculatorImpl) calculator;

        ctx.close();
    }

}
