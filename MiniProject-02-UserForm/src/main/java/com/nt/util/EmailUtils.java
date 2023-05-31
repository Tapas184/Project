package com.nt.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nt.entity.UserEntity;
@Service
public class EmailUtils {

	@Autowired
	private JavaMailSender mailsender;

	public boolean sendMail(UserEntity user) {
		try {
			
			
			MimeMessage mail = mailsender.createMimeMessage();
			mail.setFrom(new InternetAddress("routtapas1995@gmail.com"));
			mail.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail()));
			mail.setSubject("Unlock Account");
			mail.setContent(getLockAccEmailBody(user), "text/html; charset=utf-8");
			mailsender.send(mail);
			
			/*
			 * SimpleMailMessage msg = new SimpleMailMessage(); msg.setTo(user.getEmail());
			 * msg.setSubject("Unlock Account"); msg.setFrom("routtapas1995@gmail.com");
			 * msg.setText(getLockAccEmailBody(user)); mailsender.send(msg);
			 * 
			 */			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getLockAccEmailBody(UserEntity user) throws IOException {
		try (FileReader fr = new FileReader("Msg_Body.html");
				BufferedReader br = new BufferedReader(fr)) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line=br.readLine();
			} // while
			String str = sb.toString();
			str=str.replace("{fname}", user.getFname());
			str=str.replace("{lname}", user.getLname());
			str=str.replace("{emailid}", user.getEmail());
			str=str.replace("{pwd}", user.getPwd());
		return str;
		} // try
		catch (Exception e) {
			return e.getMessage();
		} // catch

	}// getLockAccEmailBody
}// class
