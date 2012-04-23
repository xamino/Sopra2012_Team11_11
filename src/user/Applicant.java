/**
 *@author Tamino Hartmann
 *@author Patryk Boczon
 *@author Manuel Guentzel
 *@author Laura Irlinger
 *@author Oemer Sahin
 *@author Anatoli Brill
 */
package user;

import javax.servlet.http.HttpSession;

import logger.Log;

import database.account.Account;
import database.account.AccountController;
import database.application.Application;

/**
 * Verwaltet alle Aufgaben und Daten eines Bewerbers.
 */
public class Applicant extends User {



	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers
	 * klasse aufgenommen.
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
	public Applicant(String username, String email, String name,
			HttpSession session) {
		super(username, email, name, session);
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

		if (!acccon.updateAccount(acc)) {
			log.write("Appllicant", "Error modifying account!");
			return false;
		}
		log.write("Applicant", "<" + getUserData().getUsername()
				+ "> modified account of <" + acc.getUsername() + ">.");
		return true;
	}

	/**
	 * Bewerben auf ein Angebot
	 * 
	 * @param offerID
	 *            ID des Angebots
	 */
	public void apply(int offerID) {
		Application app = new Application(getUserData().getName(), offerID,
				false, null, false);
		appcon.updateApplication(app);

	}

	public boolean deleteApplication(int applicationID) {
		Application app = new Application(this.getUserData().getUsername(),
				applicationID, false, "", false);
		return appcon.deleteApplication(app);
	}
}
