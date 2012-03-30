package user;

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
	 * Userdata Ojekt, das die zum User gehoerenden Daten speichert
	 */
	private UserData uData;

	/**
	 * Gibt das Userdata Objekt zurueck
	 * 
	 * @return UserData Objekt
	 */
	public UserData getUserData() {
		return uData;
	}

	/**
	 * Setz das Userdata Objekt auf das uebergebene
	 * 
	 * @param uData
	 *            zu setzendes UserData Objekt
	 */
	public void setUserData(UserData uData) {
		this.uData = uData;
	}

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
	public User() {
	};

	/**
	 * Konstruktor
	 * 
	 * @param username
	 *            Benutzername im System
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Realer Name
	 * @param session
	 *            Session des Benutzers
	 */
	public User(String username, String email, String name, HttpSession session) {
		uData = new UserData(username, email, name, session);
	}

	/**
	 * Invalidiert die Session des Benutzers
	 */
	public void invalidate() {
			uData.getSession().invalidate();
	}

	/**
	 * Standard toString()
	 * 
	 * @return String repraesentation des Objekts
	 */
	@Override
	public String toString() {
		String type = "";
		if (this instanceof Admin)
			type = "Admin";
		else if (this instanceof Provider)
			type = "Provider";
		else if (this instanceof Clerk)
			type = "Clerk";
		else if (this instanceof Applicant)
			type = "Applicant";

		return "User [Type=" + type + " Name=" + uData.getName() + ", Username="
				+ uData.getUsername() + ", Email=" + uData.getEmail() + ", SessionID="
				+ uData.getSession().getId() + "]";
	}

}
