package user;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import logger.Log;

import userManagement.LoggedInUsers;

import database.account.Account;
import database.account.AccountController;
import database.document.Document;
import database.institute.Institute;
import database.offer.Offer;
import database.offer.OfferController;

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
		super(username, email, name, null,session);
		userManagement.LoggedInUsers.addUser(this);
		this.log = Log.getInstance();
	}

	/**
	 * Löscht einen neuen Account.
	 * 
	 * @param username
	 *            Username des anzulegenden Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */

	public boolean deleteAccount(String username) {
		Account account = acccon.getAccountByUsername(username);
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
		//check if it's a provider account
		if(account.getAccounttype() == 1){
			acccon.deleteProviderAccount(new Provider(account.getUsername(), account.getEmail(), account.getName(), null));
		}
		else{
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> deleted account with username <" + username + ">");
			acccon.deleteAccount(account);
		}

		return true;
	}

	/**
	 * Methode zum Löschen seines Accounts
	 * 
	 * @return Beim erfolgreichen Entfernen wird ein TRUE zurückgegeben.
	 */
	public boolean deleteOwnAccount() {
		String username = this.getUserData().getUsername();
		Account acc = acccon.getAccountByUsername(username);
		boolean check = acccon.deleteAccount(acc);
		invalidate();

		return check;
	}

	/**
	 * Erstellt einen Account.
	 * 
	 * @param account
	 *            zu loeschender Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean createAccount(Account account) {
		if (!acccon.createAccount(account)) {
			// This can happen if the institute doesn't exist:
			log.write("Admin",
					"Error creating account! Is the institute valid?");
			return false;
		}
		log.write("Admin", "<" + getUserData().getUsername()
				+ "> created account for <" + account.getUsername() + ">.");
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
		if (!acccon.updateAccount(account)) {
			log.write("Admin", "Error modifying account!");
			return false;
		}
		log.write("Admin", "<" + getUserData().getUsername()
				+ "> modified account of <" + account.getUsername() + ">.");
		return true;
	}

	/**
	 * Loescht einen loescht ein Administrator Dokument aus der Datenbank.
	 * 
	 * @param doc
	 *            zu löschendes Dokument.
	 */
	public boolean deleteDoc(Document doc) {
		if (!doccon.deleteDocument(doc)) {
			log.write("Admin", "Error deleting document!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> deleted document <" + doc.getName() + ">.");
			return true;
		}
	}

	/**
	 * Fuegt ein Administrator Dokument hinzu.
	 * 
	 * @param doc
	 *            Das zu erstellende Dokument.
	 * @return Gibt an, ob das Dokument erstellt worden konnte.
	 */
	public boolean addDoc(Document doc) {
		if (!doccon.createDocument(doc)) {
			log.write("Admin", "Error adding a document!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> added document <" + doc.getName() + ">.");
			return true;
		}
	}

	/**
	 * Editiert ein Administrator Dokument.
	 * 
	 * @param doc
	 *            geaendertes Dokument
	 * @return Gibt an, ob das Editieren erfolgreich war.
	 */
	public boolean editDoc(Document doc) {
		if (!doccon.updateDocument(doc)) {
			log.write("Admin", "Error updating document!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> modified document <" + doc.getName() + ">.");
			return true;
		}
	}

	/**
	 * 
	 * @param institute
	 * @return
	 */
	public boolean addInstitute(Institute institute) {
		if (!instcon.addInstitute(institute)) {
			log.write("Admin", "Error creating institute!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> added institute <" + institute.getName() + ">.");
			return true;
		}
	}
	
	public boolean deleteInstitute(Institute institute) {
		if (!instcon.deleteInstitute(institute)) {
			log.write("Admin", "Error deleting institute!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> deleted institute <" + institute.getName() + ">.");
			return true;
		}
	}
}