package userManagement;

import javax.servlet.http.HttpSession;

import database.account.Account;

/**
 * Verwaltet alle Aufgaben und Daten eines Anbieters.
 */
public class Provider extends User {

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

	}

	/**
	 * Editiert den eigenen Account. Der Benutzername ist dabei nicht aenderbar
	 * und identifiziert den zu aendernden Account in der Datenbank
	 * 
	 * @param acc
	 *            geaenderter Account
	 */
	public void editAccount(Account acc) {

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
