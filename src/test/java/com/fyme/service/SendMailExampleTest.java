package com.fyme.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMailExampleTest {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springMailContext.xml");
		SendMailExample bean = (SendMailExample) context.getBean("mailService");

		//bean.sendMail("saa.kulkarni@gmail.com", "saa.kulkarni@gmail.com", "FYME email test",
		//		"Testing.. \n Hello Spring Email Sender");
		//bean.sendMailWithTemplate("User", "Welcome to FYME");
		bean.sendMailWithAttachment("User", "Welcome to FYME");
		context.close();
	}

}
