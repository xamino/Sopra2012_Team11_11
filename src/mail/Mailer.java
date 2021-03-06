/**
 * @author Manuel Guentzel
 */
package mail;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import logger.Log;
import servlet.Helper;
import config.Configurator;
import config.IllegalTypeException;
import config.UnknownOptionException;

/**
 * Klasse fuer den Mailversand
 * 
 */
public class Mailer {
	/**
	 * Username des Sendenden GMail Accounts
	 */
	private String username;
	/**
	 * Passwort des Sendenden GMail Accounts
	 */
	private String password;
	/**
	 * Objekt fuer Einstellungen des Mailversands
	 */
	private Properties props;
	/**
	 * Instanz des Loggers
	 */
	private Log log;
	/**
	 * Eigene Instanz, da Singleton
	 */
	private static Mailer instance;

	/**
	 * Gibt die Instanz des Mailers zurueck und erstellt falls noch nicht
	 * vorhanden eine solche.
	 * 
	 * @return Mailer Instanz
	 */
	public static Mailer getInstance() {
		if (instance == null)
			instance = new Mailer();
		return instance;
	}

	/**
	 * Privater Konstruktor, da Singleton
	 */
	private Mailer() {
		log = Helper.log;
		// username = "donotreply.hiwiboerse@gmail.com";
		// password = "Team11_11rockt";
		try {
			username = Configurator.getInstance().getString("GMailUsername");
			password = Configurator.getInstance().getString("GMailPassword");
			// Exception können nur geworfen werden falls der Code ungültig ist,
			// deshalb keine weitere Fehlerbehandlung.
		} catch (IllegalTypeException e) {
			System.out.println("Hast du die aktuelle CONFCONF?");
		} catch (UnknownOptionException e) {
			System.out.println("Hast du die aktuelle CONFCONF?");
		}
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	/**
	 * Funktion zur Authentifikation mit dem Mailserver
	 * 
	 * @return authenifizierte Session beim Mailserver
	 */
	private Session authenticate() {
		return Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * Funktion zum ueberpruefen der Zieladresse.
	 * 
	 * @param address
	 *            zu pruefende Adresse
	 * @return <code>True</code>, falls die Mail Adresse korrekt ist.
	 *         <code>False</code>, falls sie es nicht ist.
	 */
	private Boolean checkAddress(String address) {
		if (!address
				.matches("^[a-zA-Z0-9]([a-zA-Z0-9]|[\\._\\-][a-zA-Z0-9])*\\@[a-zA-Z0-9]([a-zA-Z0-9]|[\\.\\-][a-zA-Z0-9])*\\.[a-zA-Z0-9]{2,255}$"))
			return false;
		return true;
	}

	/**
	 * Funktion zum Versenden einer EMail via Googlemail. Im Nachrichtenkoerper
	 * koennen via \n Absaetze und Leerzeilen produziert werden.
	 * 
	 * @param address
	 *            Zieladresse
	 * @param subject
	 *            Betreff der Mail
	 * @param message
	 *            Nachrichtentext
	 * @return Boolean der beschreibt ob der Vorgang erfolgreich war.
	 */
	public Boolean sendMail(String address, String subject, String message) {
		try {
			if (!checkAddress(address))
				throw new MalformedAddressException();
			MailTread m = new MailTread();
			m.config(authenticate(), username, address, subject, message);
			m.start();
			return true;

		} catch (MalformedAddressException e) {
			log.write("Mailer",
					"Could not deliver Message. Destination address malformed.");
			return false;
		}
	}
}