package com.hello.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * 邮件发送类
 * 
 * @author Administrator
 */
public class EmailUtils {
	
	public static String url;
	
	private static String email_from_smtp;
	private static String email_from_user;
	private static String email_from_pass;
	private static String email_from_subject;
	private static String encoding;
	
	static{
		url=EmailUtils.class.getClassLoader().getResource("").getPath().replaceAll("classes", "config")+"email.properties";
		fillColumn();
	}
	
	public static void fillColumn(){
		Properties prop=new Properties();
		try {
			InputStream in=new FileInputStream(new File(url));
			prop.load(in);
			email_from_smtp=prop.getProperty("email.smtp");
			email_from_user=prop.getProperty("email.user");
			email_from_pass=prop.getProperty("email.password");
			email_from_subject=prop.getProperty("email.subject");
			encoding=prop.getProperty("encoding");
		} catch (Exception e) {
			System.out.println("Email\t"+e.getMessage());
		}
	}
	
	
	/**
	 * 发送简单文本邮件
	 * @param email_to
	 * @param msg
	 */
	public static void sendSimpleHtml(String email_to,String msg) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setCharset(encoding);
			// 设置发送主机的服务器地址
			email.setHostName(email_from_smtp);
			// 设置收件人邮箱
			email.addTo(email_to);
			// 发件人邮箱
			email.setFrom(email_from_user);
			// 如果要求身份验证，设置用户名、密码，分别为发件人在邮件服务器上注册的用户名和密码
			email.setAuthentication(email_from_user, email_from_pass);
			// 设置邮件的主题
			email.setSubject(email_from_subject);
			// 正文消息
			email.setMsg(msg);
			email.send();
			System.out.println("The SimpleEmail send sucessful!!!");
		} catch (Exception e) {
			System.out.println("The SimpleEmail to "+email_to+" send falure! The reason is "+e.getClass() + "\t" + e.getMessage());
		}
	}

	/**
	 * 发送带有附件的邮件
	 * @param complexPath	附件绝对路劲
	 * @param complexDiscriptioin
	 * 						附件描述
	 * @param complexName   附件名称
	 * @param email_to		收件人
	 * @param msg			信息
	 */
	public static void sendComplex(String complexPath,String complexDiscriptioin, String complexName ,String email_to,String msg) {
		try {
			// 创建一个Email附件
			EmailAttachment emailattachment = new EmailAttachment();
			emailattachment.setPath(complexPath);
			// emailattachment.setURL(new
			// URL("http://www.blogjava.net/bulktree/picture/bulktree.jpg"));
			emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
			emailattachment.setDescription(complexDiscriptioin);
			emailattachment.setName(complexName);
			// 创建一个email
			MultiPartEmail multipartemail = new MultiPartEmail();
			multipartemail.setHostName(email_from_smtp);
			multipartemail.addTo(email_to);
			multipartemail.setFrom(email_from_user);
			multipartemail.setAuthentication(email_from_user, email_from_pass);
			multipartemail.setSubject(email_from_subject);
			multipartemail.setMsg(msg);
			
			// 添加附件
			multipartemail.attach(emailattachment);
			// 发送邮件
			multipartemail.send();
			System.out.println("The attachmentEmail send sucessful!!!");
		} catch (Exception e) {
			System.out.println("The SimpleEmail send falure! The reason is "+e.getClass() + "\t" + e.getMessage());
		}
	}


}
