package com.nt.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nt.entity.UserEntity;

@Component("mail")
public class EmailUtils {

	@Autowired
	private JavaMailSender mailsender;

	public boolean sendMail(UserEntity user) {
		try {
			MimeMessage msg = mailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg);
			helper.setTo(user.getEmail());
			helper.setSubject("Activate Your Account");
			helper.setText(getLockAccEmailBody(user), true);
			mailsender.send(msg);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getLockAccEmailBody(UserEntity user) throws IOException {
		try (FileReader fr = new FileReader("/MiniProject-02-UserForm/Msg_Body.txt");
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
