package com.test.web.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletContext servletContext = req.getServletContext();//servlet-api 2.5 没有该方法
        ServletContext servletContext = getServletContext();

        //ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        DateFormat df = applicationContext.getBean(DateFormat.class);

        System.out.println(df.format(new Date()));
    }

}
