package com.test;

import com.test.genericDependencyInject.bean.User;
import com.test.genericDependencyInject.service.UserService;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestsGdi {

    @Test
    public void test01() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans-gdi.xml");

        UserService userService = ctx.getBean(UserService.class);

        User user = userService.findById(1);

        System.out.println(user);

        ctx.close();
    }

}
