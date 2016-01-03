package com.fyme.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service("velocityMailService")
public class VelocityEmailService {

	private VelocityEngine velocityEngine;
	private JavaMailSender mailSender;

	@Autowired
	public VelocityEmailService(VelocityEngine velocityEngine, JavaMailSender mailSender) {
		this.velocityEngine = velocityEngine;
		this.mailSender = mailSender;
	}

	public void sendMail(final SimpleMailMessage msg, final Map<String, Object> templateVariables) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(msg.getTo());
				message.setFrom(msg.getFrom());
				message.setSubject(msg.getSubject());

				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/emailBody.vm",
						templateVariables);
				message.setText(body, true);
			}
		};
		mailSender.send(preparator);
	}
}
