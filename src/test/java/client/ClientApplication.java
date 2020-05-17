package client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import base.BaseTestCase;

public class ClientApplication {
	
	
    public static void main(String[] args) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    	System.out.println("beans loaded");
    	BaseTestCase basepage=context.getBean("base",BaseTestCase.class);
    	System.out.println(basepage.getBrowser());
	}

}
