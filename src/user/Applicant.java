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

import database.account.Account;

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
	 * Bewerben auf ein Angebot
	 * 
	 * @param offerID
	 *            ID des Angebots
	 */
	public void apply(int offerID) {

	}

	/**
	 * Diese Methode entfernt ein bestimmtes Bewerbungs-Objekt von der Datenbank.
	 * @param applicationID
	 * Parameter "applicant" ist eindeutiger Identifizierer des Bewerbungs-Objekts.
	 */
	public void deleteApplication(int applicationID){

	}
}
