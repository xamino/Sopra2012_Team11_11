package user;

import javax.servlet.http.HttpSession;

import database.account.Account;
import database.document.Document;

/**
 * Verwaltet alle Aufgaben und Daten eines Admins.
 */
public class Admin extends User {

	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers
	 * klasse aufgenommen.
	 * 
	 * @param username
	 *            Benutzername im System.
	 * @param email
	 *            E-Mail Adresse.
	 * @param name
	 *            Realer Name.
	 * @param session
	 *            Session des Benutzers.
	 */
	public Admin(String username, String email, String name, HttpSession session) {
		super(username,email,name,session);
		userManagement.LoggedInUsers.addUser(this);
	}

	/**
	 * Erstellt einen neuen Account.
	 * 
	 * @param acc
	 *            anzulegender Account.
	 */

	public void createAccount(Account acc) {

	}

	/**
	 * Löscht einen Account.
	 * 
	 * @param acc
	 *            zu loeschender Account.
	 */
	public void deleteAccount(Account acc) {

	}

	/**
	 * Editiert einen Account. Der Benutzername ist dabei nicht aenderbar und
	 * identifiziert den zu aendernden Account in der Datenbank.
	 * 
	 * @param acc
	 *            geaenderter Account.
	 */
	public void editAccount(Account acc) {

	}

	/**
	 * Loescht einen loescht ein Administrator Dokument aus der Datenbank.
	 * 
	 * @param doc
	 *            zu löschendes Dokument.
	 */
	public void deleteDoc(Document doc) {

	}

	/**
	 * Fuegt ein Administrator Dokument hinzu.
	 * 
	 * @param doc
	 */
	public void addDoc(Document doc) {

	}

	/**
	 * Editiert ein Administrator Dokument.
	 * 
	 * @param doc
	 *            geaendertes Dokument
	 */
	public void editDoc(Document doc) {

	}

}