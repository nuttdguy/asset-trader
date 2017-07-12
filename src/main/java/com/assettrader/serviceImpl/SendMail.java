package com.assettrader.serviceImpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.assettrader.model.utils.EmailProviderName;

public class SendMail {

	public synchronized void sendEmails(String toEmail, String fromEmail, String msgSubject, String msg,
			String fromEmailAccountPassword, EmailProviderName emailProvider) {

		
		// FOR GMAIL ACCOUNT
		
		if (EmailProviderName.GMAIL.name().equals("GMAIL")) {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, fromEmailAccountPassword);
				}
			});

			try {
				Message message = new MimeMessage(mailSession);

				message.setFrom(new InternetAddress(fromEmail));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
				message.setSubject(msgSubject);
				message.setText(msgSubject);

				Transport.send(message);

				System.out.println("Message was sent successfully");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			notifyAll();
		}

		
		// FOR YAHOO ACCOUNT
		
		if (EmailProviderName.YAHOO.name().equals("YAHOO")) {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.mail.yahoo.com"); // for gmail use
																// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, fromEmailAccountPassword);
				}
			});

			try {
				Message message = new MimeMessage(mailSession);

				message.setFrom(new InternetAddress(fromEmail));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
				message.setSubject(msgSubject);
				message.setText(msgSubject);

				Transport.send(message);

				System.out.println("Message was sent successfully");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			notifyAll();
		}

	}

}
