package fis.his.case_workers_management.utils.mail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import fis.his.case_workers_management.model.CwAndAdPojo;

@Service
public class MailUtilsImpl implements MailInterface {
	
	@Autowired
	private JavaMailSender mailsender;
	@Override
	public boolean sendMail(CwAndAdPojo pojo){
		try {
		MimeMessage mail = mailsender.createMimeMessage();
		mail.setFrom(new InternetAddress("routtapas1995@gmail.com"));
		mail.setRecipient(RecipientType.TO, new InternetAddress(pojo.getEmailid()));
		mail.setSubject("Unlock Account");
		mail.setContent(getLockAccEmailBody(pojo), "text/html; charset=utf-8");
		mailsender.send(mail);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	private String getLockAccEmailBody(CwAndAdPojo pojo) throws IOException {
		FileReader file = new FileReader("Msg_Body.html");
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(file)){
		String readLine = br.readLine();
		
		while(readLine!=null) {
			sb.append(readLine);
			readLine=br.readLine();
		}
		return sb.toString().replace("{fname}", pojo.getFname())
		   .replace("{lname}", pojo.getLname())
		   .replace("{pwd}", pojo.getPwd())
		   .replace("{emailid}", pojo.getEmailid());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean sendMailForRestPawword(CwAndAdPojo pojo) throws Exception {

		try {
			MimeMessage mail = mailsender.createMimeMessage();
			mail.setFrom(new InternetAddress("routtapas1995@gmail.com"));
			mail.setRecipient(RecipientType.TO, new InternetAddress(pojo.getEmailid()));
			mail.setSubject("Unlock Account");
			mail.setContent(passwordRestEmailBody(pojo), "text/html; charset=utf-8");
			mailsender.send(mail);
			return true;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}
	
	private String passwordRestEmailBody(CwAndAdPojo pojo) throws IOException {
		FileReader file = new FileReader("PassWord_Reset_Mail_Body.html");
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(file)){
		String readLine = br.readLine();
		
		while(readLine!=null) {
			sb.append(readLine);
			readLine=br.readLine();
		}
		return sb.toString().replace("{fname}", pojo.getFname())
		   .replace("{lname}", pojo.getLname())
		   .replace("{pwd}", pojo.getPwd());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
