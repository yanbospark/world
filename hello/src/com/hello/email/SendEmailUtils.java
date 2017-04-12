package com.hello.email;


/**
 * 发送邮件
 * @author yanbo
 */
public class SendEmailUtils {
	static String host="";
	static String port="";
	static String mail_account="";
	static String mail_password="";
	
	static{
		reloadConfig();
	}
	
	
	/**
	 * 发送邮件
	 */
	public static boolean sendMail(String content, String title,String toAddress) {
		try {
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(host);
			mailInfo.setMailServerPort(port);
			mailInfo.setValidate(true);
			// 邮箱账号密码
			mailInfo.setUserName(mail_account);
			mailInfo.setPassword(mail_password);
			// 邮件发送者的地址
			mailInfo.setFromAddress(mail_account);

			mailInfo.setToAddress(toAddress);
			mailInfo.setSubject(title);
			mailInfo.setContent(content);

			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			// 发送文体格式
			return sms.sendTextMail(mailInfo);

		} catch (Exception e) {
			System.out.println("发送邮件异常："+e);
			return false;
		}
	}
    
	//重新加载配置
	public static void reloadConfig(){
//		host = DBUtils.getString("select stringValue from SystemConfig where keyName=? and isHidden=0", new Object[] { Constants.MAIL_SERVERHOST });
//		port = DBUtils.getString("select stringValue from SystemConfig where keyName=? and isHidden=0", new Object[] { Constants.MAIL_SERVERPORT });
//		mail_account = DBUtils.getString("select stringValue from SystemConfig where keyName=? and isHidden=0", new Object[] { Constants.MAIL_ACCOUNT });
//		mail_password = DBUtils.getString("select stringValue from SystemConfig where keyName=? and isHidden=0", new Object[] { Constants.MAIL_PASSWORD });
	}
	
	public static void main(String[] args) {

		System.out.println(sendMail("测试<br /> test", "购买点卡获取卡密",
				"yanbo@dodonew.com"));

	}

}
