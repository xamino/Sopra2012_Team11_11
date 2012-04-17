package user;

import javax.servlet.http.HttpSession;

import logger.Log;


import database.account.Account;
import database.account.AccountController;

/**
 * Verwaltet alle Aufgaben und Daten eines Anbieters.
 */
public class Provider extends User {

	/**
	 * Private Instanz des Loggers.
	 */
	private Log log;
	/**
	 * Private Instanz des AccountController.
	 */
	private AccountController accountController;
	
	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der
	 * LoggedInUsers-Klasse aufgenommen.
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
	public Provider(String username, String email, String name,
			HttpSession session) {
		super(username,email,name,session);
		userManagement.LoggedInUsers.addUser(this);

	}

	/**
	 * Editiert den eigenen Account. Der Benutzername ist dabei nicht aenderbar
	 * und identifiziert den zu aendernden Account in der Datenbank
	 * 
	 * @param acc
	 *            geaenderter Account
	 */
	public boolean editAccount(Account acc) {
		
		if (!accountController.updateAccount(acc)) {
			log.write("Appllicant", "Error modifying account!");
			return false;
		}
		log.write("Applicant", "<" + getUserData().getUsername()
				+ "> modified account of <" + acc.getUsername() + ">.");
		return true;
	}

	/**
	 * Methode zur Annahme von Bewerbern auf ein Angebot.
	 */
	public void acceptApplicants() {

	}

	/**
	 * Loescht ein Angebot aus dem System.
	 */
	public void deleteOffer() {

	}

	/**
	 * Erstellt ein neues, noch zu pruefendes Angebot im System. 
	 */
	public void createOffer() {

	}

}
