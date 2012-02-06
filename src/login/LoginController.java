package login;


/**
 * Hilfsklasse fuer den Loginvorgang des Systems.
 */
public abstract class LoginController {

	/**
	 * Ueberprueft vor dem einlogen mit gegebenen Daten, ob
	 * die Parameter einem Account entsprechen. 
	 * 
	 * @param username
	 *            Benutzername eines Accounts.
	 * @param password
	 *            Password des Benutzers fuer den Account.
	 * @return <code>True</code> falls Benutzername und Accountpasswort
	 *         korrektsind , sonst <code>False</code>.
	 */
	public static boolean loginCheck(String username, String password) {

		return false;
	}

	/**
	 * Wandelt den Passwort-String in einen kryptischen Hash-Wert um. Das
	 * Benutzerpasswort darf aus Sicherheitsgruenden NICHT als Klartext
	 * abgespeichert werden, deshalb wird nur der umgewandelte String in der
	 * Datenbank abgelegt.
	 * 
	 * @param s
	 *            Passwort-String eines Accounts.
	 * @return Verschluesselter Hash-Wert als String
	 */
	public static String hash(String s) { // gibt md5 hash eines strings zurück

		return "";
	}

}
