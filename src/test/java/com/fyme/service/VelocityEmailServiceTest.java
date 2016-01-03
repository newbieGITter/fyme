package com.fyme.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;

public class VelocityEmailServiceTest {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springMailContext.xml");
		VelocityEmailService velocityMailService = (VelocityEmailService) context.getBean("velocityMailService");
		SimpleMailMessage simpleMailMessage = (SimpleMailMessage) context.getBean("mailMessage");

		// bean.sendMail("saa.kulkarni@gmail.com", "saa.kulkarni@gmail.com",
		// "FYME email test",
		// "Testing.. \n Hello Spring Email Sender");
		// bean.sendMailWithTemplate("User", "Welcome to FYME");
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("firstName", "Saahas");
		props.put("lastName", "Kulkarni");

		velocityMailService.sendMail(simpleMailMessage, props);
		context.close();
	}
}
