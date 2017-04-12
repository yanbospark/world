package com.hello.email;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @description
 * @author li.wang
 * @date 2014-5-29下午2:29:03
 */
public class SimpleMailSender {

	public boolean sendTextMail(MailSenderInfo mailInfo) throws Exception {

		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailInfo.getToAddress(), true));
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			mailMessage.setContent(mailInfo.getContent(), "text/html;charset=UTF-8");
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			throw new Exception(ex);
		}
	}
}
