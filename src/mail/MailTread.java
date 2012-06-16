package mail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import logger.Log;

/**
 * Thread für den eigentlichen Mailversand
 * @author Guentzel
 *
 */
public class MailTread extends Thread {
	/**
	 * Log fuer Systemausgaben
	 */
	private Log log= Log.getInstance();
	/**
	 * Absender der Mail
	 */
	private String sender;
	/**
	 * Adresse des Ziels
	 */
	private String addr;
	/**
	 * Subject der Mail
	 */
	private String subject;
	/**
	 * Text der Mail
	 */
	private String text;
/**
 * Session zum versenden
 */
	private Session ses;
	@Override
	/**
	 * Run Methode des Threads in dem dann die Mail versandt wird.
	 */
	public void run() {
		try{
		Message msg = new MimeMessage(ses);
		msg.setFrom(new InternetAddress(sender));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addr));
		msg.setSubject(subject);
		msg.setText(text);
		Transport.send(msg);
		log.write("Mailer", "Message to "+addr+" delivered.");
		}catch (Exception e) {
			e.printStackTrace();
			log.write("MailThread", "There was a problem while sending mail to "+ addr);
		}
	}
	
    /**
     * Methode die die Parameter festlegt.
     * @param ses Session zum versenden
     * @param sender Absender der Mail
     * @param addr Adresse des Ziels
     * @param subject Betreff der Nachricht
     * @param text Text der Nachricht 
     */
	public  void config(Session ses,String sender,String addr, String subject, String text) {
		this.ses=ses;
		this.sender=sender;
		this.addr=addr;
		this.subject=subject;
		this.text=text;
	}
	
	
}
