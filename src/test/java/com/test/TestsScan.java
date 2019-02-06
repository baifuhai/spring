package com.test;

import com.test.scan.dao.PersonDao;
import com.test.scan.service.PersonService;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestsScan {

    @Test
    public void test01() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scan.xml");

        PersonService personService = (PersonService) ctx.getBean("personService");
        PersonDao personDao = (PersonDao) ctx.getBean("personDao");
        //PersonDao personDaoSub = (PersonDao) ctx.getBean("personDaoSub");

        System.out.println(personService);
        System.out.println(personDao);
        //System.out.println(personDaoSub);

        ctx.close();
    }

}
