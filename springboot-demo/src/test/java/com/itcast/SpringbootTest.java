package com.pinyougou;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {
    @Autowired
    private Person persons;
    @Autowired
    private ApplicationContext ioc;
    @Test
    public void test(){
        System.out.println(persons);
    }

    @Test
    public void testBeanService(){
        boolean helloService = ioc.containsBean("helloService");
        System.out.println(helloService);
    }
}
