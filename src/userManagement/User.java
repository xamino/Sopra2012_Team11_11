package userManagement;

import javax.servlet.http.HttpSession;

import database.account.AccountController;
import database.application.ApplicationController;
import database.document.DocumentController;
import database.offer.OfferController;
/**
 * Vorlage fuer Admin,Anbieter,Verwalter und Bewerber.
 */
public abstract class User {

	
	/**
	 * AccountController fuer Datenbankzugriff auf accountbezogene Daten.
	 */
	public AccountController acccon;
	/**
	 * ApplicationController fuer Datenbanzugriff auf bewerbungsbezogene Daten.
	 */
	public ApplicationController appcon;
	/**
	 * DocumentController fuer Datenbankzugriff auf unterlagenbezogene Daten.
	 */
	public DocumentController doccon;
	/**
	 * OfferController fuer den Datenbankzugriff auf angebotsbezogene Daten.
	 */
	public OfferController offcon;
	
	/**
	 * empty standard Constructor
	 */
	public User(){};
	/**
	 * Konstruktor
	 * 
	 * @param benutzername
	 *            Benutzername im System
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Realer Name
	 * @param session
	 *            Session des Benutzers
	 */
	public User(String benutzername, String email, String name,
			HttpSession session) {
		this.benutzername = benutzername;
		this.email = email;
		this.name = name;
		this.session = session;
	}

	/**
	 * Standard toString()
	 * 
	 * @return String repraesentation des Objekts
	 */
	@Override
	public String toString() {
		return "";
	}

	private String name;
	/**
	 * Benutzername des Benutzers
	 */
	private String benutzername;
	/**
	 * E-Mail Adresse des Benutzers
	 */
	private String email;
	/**
	 * Session des Benutzers
	 */
	private HttpSession session;

	/**
	 * Gibt den Realamen zurueck
	 * 
	 * @return Realname
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setz den Realnamen
	 * 
	 * @param name
	 *            Gewuenschter Realname
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Benutzernamen zurueck
	 * 
	 * @return Benutzername
	 */
	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * Setzt den Benutzernamen
	 * 
	 * @param benutzername
	 *            Gewuenschter Benutzername
	 */
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	/**
	 * Gibt die Emailadresse des Benutzers zurueck
	 * 
	 * @return Emailadresse des Benutzers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzt die E-Mailadresse
	 * 
	 * @param email
	 *            Gewuenschte E-Mailadresse
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gibt die Session des Benutzers zurueck
	 * 
	 * @return Session des Benutzers
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * Setzt die Session des Benutzers
	 * 
	 * @param session
	 *            Gewuenschte Session
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

}
