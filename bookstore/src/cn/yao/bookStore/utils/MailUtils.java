package cn.yao.bookStore.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	public static void sendMail(String toEmail, String emailMsg) {
//				Random a = new Random();
//				int activeCode = a.nextInt(100000);//产生随机数，用于产生验证码
//应该写一个工具类来产生激活码
		Session session = getSession();
		MimeMessage msg = new MimeMessage(session);
		try {
			// 发送邮件的邮箱
			msg.setFrom(new InternetAddress("zhangyao4258@163.com"));
			// 用来接收验证的邮箱
			msg.setRecipient(RecipientType.TO, new InternetAddress(toEmail));

			msg.setSubject("bookStores激活账户");
			msg.setSentDate(new Date());
			msg.setContent(emailMsg, "text/html;charset=utf-8");
			Transport.send(msg);

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Session getSession() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");// 指定发送的邮箱的邮箱协议
		props.setProperty("mail.smtp.host", "smtp.163.com");// 指定发邮件的邮箱，SMTP服务器
		props.setProperty("mail.smtp.port", "25"); // smtp是发信邮件服务器,端口是25
		props.setProperty("mail.smtp.auth", "true");// 指定是否需要SMTP验证

		props.setProperty("mail.smtp.starttls.enable", "true");
		// 这里很重要，如果没有这句，这会引起安全配置问题，无法通过验证 !!!

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zhangyao4258@163.com", "zhangyao1220");
			}
		});
		return session;
	}
}
