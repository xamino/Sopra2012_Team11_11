package userManagement;

import javax.servlet.http.HttpSession;

import user.Admin;
import user.Applicant;
import user.Clerk;
import user.Provider;
import user.User;

import logger.Log;

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
		int type = acc.getAccounttype();
		if(type==0){
			return new Admin(acc.getUsername(),acc.getEmail(),acc.getName(),session);
		}else if(type==1){
			return new Provider(acc.getUsername(),acc.getEmail(),acc.getName(),session);
		}else if(type==2){
			return new Clerk(acc.getUsername(),acc.getEmail(),acc.getName(),session);
		}else if(type==3){
			return new Applicant(acc.getUsername(),acc.getEmail(),acc.getName(),session);
		}else return null;
	}
}