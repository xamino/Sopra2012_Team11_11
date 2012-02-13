package userManagement;

import javax.servlet.http.HttpSession;

import database.account.Account;

/**
 * Factoryklasse zum erzeugen von Userobjekten.
 */
public class UserFactory {

	/**
	 * Diese Methode erzeugt eine User-Instanz des entsprechenden Accounttyps.
	 * [0 - Admin] [1 - Anbieter] [2 - Verwalter] [3 - Bewerber]
	 * 
	 * @param acc
	 *            Account des Benutzers der zu erzeugenden User-Instanz
	 * @param session
	 *            HttpSession mit der der Benutzer verknuepft werden soll
	 * @return User-Instanz entsprechend dem Accounttyp (Admin,Anbieter,Bewerber
	 *         oder Verwalter)
	 */
	public static User getUserInstance(Account acc, HttpSession session) {
		return null;
	}
}