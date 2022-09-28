package com.wzh.secondshop.utils;

import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;




public class SendEmailUtil{
	public static void main(String srgs[]) {
		SendEmailUtil seu= new SendEmailUtil();
		seu.setPwd("xkdsuqllibnndbcd");
		seu.setSendEmail("1677452196@qq.com");
		seu.sendHtmlMail("1028469496@qq.com", "a", "fuck");
		
	}

	public String sendEmail;
	public String pwd;
	
	public void setSendEmail(String sendEmail) {
		this.sendEmail =sendEmail;
	}
	
	public void setPwd(String pwd) {
		this.pwd=pwd;
		
	}
	
	public boolean sendHtmlMail(String receiveEmail,String name,String code) {
		if(StringUtil.checkNull(receiveEmail, name, code)) {
		return false;
		}
		
		
		System.out.print('\n'+"收件人--------------------"+receiveEmail+"--------------------------");//成功
		System.out.print('\n'+"发件人--------------------"+sendEmail+"--------------------------");//成功
		
		try {
			JavaMailSenderImpl senderImpl =new JavaMailSenderImpl();
			senderImpl.setHost("smtp.qq.com");
			senderImpl.setDefaultEncoding("utf-8");
	
			MimeMessage message =senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper =new MimeMessageHelper(message);
			
			messageHelper.setTo(receiveEmail);
			
			messageHelper.setFrom(sendEmail);
			
			messageHelper.setSubject("找回密码");
			
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE");
			
			String txt ="<!dovtype html>"
					+ "<html>"
					+ "<head>"
							+ "<meta charset=\"utf-8\">"
					+ "</head>"
					+ "<body>"
							+ "<a>success!password  is :"+code+"</a>"
					+ "</body>"
					+ "</html>";
			
			messageHelper.setText(txt, true);
			
			senderImpl.setUsername(sendEmail);
			senderImpl.setPassword(pwd);
			
			Properties prop =new Properties();
			prop.put("mail.smtp.auth","true");
			prop.put("mail.stmp.timeout", "1800");
			
			senderImpl.setJavaMailProperties(prop);
			senderImpl.send(message);
			
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	return false;
	
	
		
	}
}	
	
	
	
	

