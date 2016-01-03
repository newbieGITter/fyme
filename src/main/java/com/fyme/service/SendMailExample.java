package com.fyme.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class SendMailExample {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage mailMessage;

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	public void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}

	public void sendMailWithTemplate(String text, String content) {
		SimpleMailMessage message = new SimpleMailMessage(mailMessage);
		message.setText(String.format(mailMessage.getText(), text, content));
		mailSender.send(message);
	}

	public void sendMailWithAttachment(String text, String content) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailMessage.getFrom());
			helper.setTo(mailMessage.getTo());
			helper.setSubject(mailMessage.getSubject());
			helper.setText(String.format(mailMessage.getText(), text, content));
			
			FileSystemResource file = new FileSystemResource("C:\\Users\\RK82TR\\soapui-settings.xml");
			helper.addAttachment(file.getFilename(), file);
			//helper.addInline("identifier1", file);
			javaMailSender.send(message);

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

}
