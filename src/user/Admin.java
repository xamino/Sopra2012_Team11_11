package user;

import javax.servlet.http.HttpSession;

import logger.Log;

import userManagement.LoggedInUsers;

import database.account.Account;
import database.account.AccountController;
import database.document.Document;

/**
 * Verwaltet alle Aufgaben und Daten eines Admins.
 */
public class Admin extends User {

	/**
	 * Private Instanz des Loggers.
	 */
	private Log log;
	/**
	 * Private Instanz des AccountController.
	 */
	private AccountController accountController;

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
		super(username, email, name, session);
		userManagement.LoggedInUsers.addUser(this);
		this.log = Log.getInstance();
		this.accountController = AccountController.getInstance();
	}

	/**
	 * Löscht einen neuen Account.
	 * 
	 * @param username
	 *            Username des anzulegenden Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */

	public boolean deleteAccount(String username) {
		Account account = accountController.getAccountByUsername(username);
		if (account == null) {
			log.write("Admin", "Can not delete <" + username
					+ ">. Does not exist!");
			// response.setContentType("text/error");
			// response.getWriter().write("This user doesn't seem to exist!");
			return false;
		}
		// If user is currently logged in, we do not allow deletion:
		if (LoggedInUsers.getUserByUsername(username) != null) {
			log.write("Admin", "Can not delete <" + username
					+ "> as currently logged in!");
			return false;
		}
		log.write("Admin", "Deleting account with username <" + username + ">");
		accountController.deleteAccount(account);
		return true;
	}

	/**
	 * Erstellt einen Account.
	 * 
	 * @param account
	 *            zu loeschender Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean createAccount(Account account) {
		if (!accountController.createAccount(account)) {
			// This can happen if the institute doesn't exist:
			log.write("Admin",
					"Error creating account! Is the institute valid?");
			return false;
		}
		log.write("Admin", "Created account for <" + account.getUsername()
				+ ">.");
		return true;
	}

	/**
	 * Editiert einen Account. Der Benutzername ist dabei nicht aenderbar und
	 * identifiziert den zu aendernden Account in der Datenbank.
	 * 
	 * @param acc
	 *            geaenderter Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean editAccount(Account account) {
		// For debugging wrong character set:
		// System.out.println(account.getName());
		if (!accountController.updateAccount(account)) {
			log.write("Admin", "Error modifying account!");
			return false;
		}
		log.write("Admin", "Modified account of <" + account.getUsername()
				+ ">.");
		return true;
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
