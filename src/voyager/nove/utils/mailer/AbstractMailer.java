package voyager.nove.utils.mailer;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @name AbstractMailer
 *
 * @project Voyager
 *
 * @package voyager.nove.utils.mailer
 *
 * @author Giacomo Marciani
 *
 */
public abstract class AbstractMailer implements Mailer {	
	
	protected Session session;

	protected AbstractMailer() {}
	
	@Override
	public void inviaMail(String mailDestinatario, String oggetto, String messaggio) {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Mailer.USERNAME, Mailer.PASSWORD);
			}
		  });
 
		try { 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Mailer.MAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDestinatario));
			message.setSubject(oggetto);
			message.setText(messaggio); 
			Transport.send(message); 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
