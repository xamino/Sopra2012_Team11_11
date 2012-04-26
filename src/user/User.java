package user;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import logger.Log;

import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.application.ApplicationController;
import database.document.AppDocument;
import database.document.DocumentController;
import database.offer.Offer;
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
	 * Private Instanz des Loggers.
	 */
	protected Log log;
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
	protected AccountController acccon;
	/**
	 * ApplicationController fuer Datenbanzugriff auf bewerbungsbezogene Daten.
	 */
	protected ApplicationController appcon;
	/**
	 * DocumentController fuer Datenbankzugriff auf unterlagenbezogene Daten.
	 */
	protected DocumentController doccon;
	/**
	 * OfferController fuer den Datenbankzugriff auf angebotsbezogene Daten.
	 */
	protected OfferController offcon;


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
		acccon = AccountController.getInstance();
		appcon = ApplicationController.getInstance();
		doccon = DocumentController.getInstance();
		offcon= OfferController.getInstance();
	}

	/**
	 * Invalidiert die Session des Benutzers
	 */
	public void invalidate() {
		uData.getSession().invalidate();
	}

	/**
	 * Loescht den eigenen Account
	 */
	
	
	/**
	 * Aendert die Daten des eigenen Accounts
	 * @param name Neuer Name
	 * @param email Neue Email
	 * @param pw Neues Passwort (gehashed!)
	 * @return Wahrheitswert ob erfolgreich
	 */
	public boolean editOwnAccount(String name, String email , String pw){
		Account own = acccon.getAccountByUsername(uData.getUsername());
		if(email!=null){
			own.setEmail(email);
			uData.setEmail(email);
		}
		if(pw!=null)own.setPasswordhash(pw);
		if(name!=null){
			own.setName(name);
			uData.setName(name);
		}
		if(acccon.updateAccount(own))return true;
		return false;
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

		return "User [Type=" + type + " Name=" + uData.getName()
				+ ", Username=" + uData.getUsername() + ", Email="
				+ uData.getEmail() + ", SessionID="
				+ uData.getSession().getId() + "]";
	}

}
