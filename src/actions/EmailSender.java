package actions;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
	private String to;
	private String from;
	private String passwordFrom;
	private Properties prop;
	private Session session;
	
	public EmailSender(String to) {		
		this.to = to;
		from = "walrussuit@gmail.com";
		passwordFrom = "";
		prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	}
	
	
	/**Will send an email to the user, will send email to what is given
	 * in the constructor
	 * 
	 * @param showDate Date for the show, will go in email's subject
	 * @param Setlist Actual setlist, will go in email's message
	 * @return Will return 0 if sent, 1 if error
	 */
	public int sendShowEmail(String showDate, String setlist) {
		
		String subject = showDate;
		
		session = Session.getInstance(prop, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, passwordFrom);
				}
			});
		
		try {
			MimeMessage messageShow = new MimeMessage(session);
			messageShow.setFrom(new InternetAddress(from));
			messageShow.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			messageShow.setSubject(subject);
			messageShow.setContent(setlist + "<BR><BR><BR><BR>THIS EMAIL WAS SENT BY PHISHPHRACKER", "text/html");
			Transport.send(messageShow);
			
			return 0;
		}catch(MessagingException e){
			System.out.println(e);
			return 1;
		}	
	}	
}