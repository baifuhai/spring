package com.test.web.listener;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    private ConfigurableApplicationContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        //1. 获取 Spring 配置文件的名称
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        //2. 创建 IOC 容器
        ctx = new ClassPathXmlApplicationContext(contextConfigLocation);

        //3. 把 IOC 容器放在 ServletContext 的一个属性中
        servletContext.setAttribute("applicationContext", ctx);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ctx.close();
    }

}
