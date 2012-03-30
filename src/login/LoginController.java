package login;


import database.account.Account;
import database.account.AccountController;

/**
 * Hilfsklasse fuer den Loginvorgang des Systems.
 */
public abstract class LoginController {
	/**
	 * Controller der Accountspezifischen Datenbankanfragen
	 */
	public static AccountController accCon = AccountController.getInstance();

	/**
	 * Ueberprueft vor dem einlogen mit gegebenen Daten, ob die Parameter einem
	 * Account entsprechen.
	 * 
	 * @param username
	 *            Benutzername eines Accounts.
	 * @param password
	 *            Password des Benutzers fuer den Account.
	 * @return <code>True</code> falls Benutzername und Accountpasswort
	 *         korrektsind , sonst <code>False</code>.
	 */
	public static boolean loginCheck(String username, String password) {
			Account acc = accCon.getAccountByUsername(username);
			if(acc==null)return false;
			if(password.equals(acc.getPasswordhash()))return true;
			return false;
	}


}
