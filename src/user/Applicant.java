/**
 *@author Tamino Hartmann
 *@author Patryk Boczon
 *@author Manuel Guentzel
 *@author Laura Irlinger
 *@author Oemer Sahin
 *@author Anatoli Brill
 */
package user;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import database.account.Account;
import database.application.Application;
import database.document.AppDocument;
import database.offer.Offer;

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
		super(username, email, name, null,session);
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
	 * Methode zum Löschen seines Accounts
	 * @return	Beim erfolgreichen Entfernen wird ein TRUE zurückgegeben.
	 * 			Falls irgendwo ein Fehler aufgetretten ist wird ein FALSE zurückgegeben.
	 */
	public boolean deleteOwnAccount(){
		invalidate();
		return acccon.deleteApplicantAccount(this);
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

	public boolean deleteApplication(int applicationID, int offerID) {
		Application app = new Application(this.getUserData().getUsername(),
				applicationID, false, "", false);
		Account account = new Account(this.getUserData().getUsername(), "", 0, "", "", 0, "");
		Offer offer = new Offer(offerID, "", "", "", true, 0, 0, "", null, null, 0, 0, null);
		Vector<AppDocument> vec = doccon.getDocumentsByUserAndOffer(account, offer);
		Iterator<AppDocument> it = vec.iterator();
		
		int i = 0;
		while(it.hasNext()){
			doccon.deleteAppDocument(vec.elementAt(i));
		}
		
		return appcon.deleteApplication(app);
	}
}
