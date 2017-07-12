package com.assettrader.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailSeviceImpl {
	
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public synchronized void sendEmail(String recipient, String sender, String msgToSend ) {
		
		MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		MimeMessageHelper mimeHelper;
		
		try {
			mimeHelper = new MimeMessageHelper(mimeMessage, true);
			mimeHelper.setTo(recipient);
			mimeHelper.setFrom(sender);
			mimeHelper.setText(msgToSend, true);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			System.out.println("Error sending mail " + e.getMessage() );
		}
		notifyAll();
		
	}
	
	

}
