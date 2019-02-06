package com.test;

import com.test.tx.service.OrderService;
import com.test.tx.service.OrderService2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestsTx {

    private ConfigurableApplicationContext ctx;
    private OrderService orderService;
    private OrderService2 orderService2;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("beans-tx.xml");
        orderService = ctx.getBean(OrderService.class);
        orderService2 = ctx.getBean(OrderService2.class);
    }

    @After
    public void after() {
        ctx.close();
    }

    @Test
    public void test01() throws Exception {
        orderService.buy(1, 1);
    }

    @Test
    public void test02() throws Exception {
        orderService2.buys(1, new int[]{1, 2});
    }

}
