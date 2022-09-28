package com.wzh.secondshop.utils;

import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.wzh.secondshop.services.UserService;



public class try1 {
	public static void main(String srgs[]) throws Exception {
		UserService userService =new UserService();
		userService.updatePasswordByemail("123321", "123@qq.com");
		
		
		
		
		
	}
	
	
	
	public boolean sendHtmlMailoo(String receiveEmail,String code) throws Exception {
		if(StringUtil.checkNull(receiveEmail, code)) {
		return false;
		}
		
		
		System.out.print('\n'+"收件人--------------------"+receiveEmail+"--------------------------");//成功
		System.out.print('\n'+"发件人--------------------"+"1677452196@qq.com"+"--------------------------");//成功
		
	
			JavaMailSenderImpl senderImpl =new JavaMailSenderImpl();
			senderImpl.setHost("smtp.qq.com");
			senderImpl.setDefaultEncoding("utf-8");
	
			MimeMessage message =senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper =new MimeMessageHelper(message);
			
			messageHelper.setTo(receiveEmail);
			
			messageHelper.setFrom("1677452196@qq.com");
			
			messageHelper.setSubject("邮箱找回密码验证码");
			
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE");
			
			String txt ="<!dovtype html>"
					+ "<html>"
					+ "<head>"
							+ "<meta charset=\"utf-8\">"
					+ "</head>"
					+ "<body>"
							+ "<a>邮箱验证成功，验证码为 :"+code+"，3分钟内有效！</a>"
					+ "</body>"
					+ "</html>";
			
			messageHelper.setText(txt, true);
			
			senderImpl.setUsername("1677452196@qq.com");
			senderImpl.setPassword("xkdsuqllibnndbcd");
			
			Properties prop =new Properties();
			prop.put("mail.smtp.auth","true");
			prop.put("mail.stmp.timeout", "1800");
			
			senderImpl.setJavaMailProperties(prop);
			senderImpl.send(message);
			
			return true;
	
	
	
		
	}
	
	

}
