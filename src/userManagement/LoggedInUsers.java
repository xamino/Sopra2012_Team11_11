package userManagement;

import java.util.Vector;

import javax.servlet.http.HttpSession;

/**
 * Verwaltet alle eingelogten User.
 */
public abstract class LoggedInUsers {

	/**
	 * Vector mit den eingelogten Usern des Systems.
	 */
	private static Vector<User> users = new Vector<User>();

	/**
	 * Gibt den Vector mit den eingeloggten Usern zurueck.
	 * 
	 * @return Vector mit eingeloggten Usern
	 */
	public static Vector<User> getUsers() {
		return users;
	}

	/**
	 * Fuegt einen User zum Vektor der eingeloggten User hinzu und behebt
	 * eventuelle Konflikte. Versucht man einen User einzuloggen der bereits
	 * eine Session im System hat, so wird die alte Session beendet.Versucht man
	 * sich als ein User anzumelden,der bereits im System angemeldet ist, so
	 * wird der bereits vorhandene Kandidat abgemeldet.
	 * 
	 * @param u
	 *            hinzuzufuegender User
	 * 
	 * 
	 */
	static void addUser(User u) {

	}

	/**
	 * Gibt ein User-Objekt mit dem übergebenen Namen zurueck.
	 * 
	 * @param username
	 *            zu suchender Benutzername
	 * @return Das gefundene User-Objekt wird zurueckgegeben, ansonsten
	 *         <code>NULL</code>
	 * @see User
	 */
	public static User getUserByUsername(String username) {

		return null;
	}

	/**
	 * Gibt ein User-Objekt mit der übergebenen Session zurueck
	 * 
	 * @param session
	 *            zu suchende Session
	 * @return Das gefundene User-Objekt wird zurueckgegeben, ansonsten
	 *         <code>Null</code>
	 **/
	public static User getUserBySession(HttpSession session) {

		return null;
	}

	/**
	 * Entfernt das User-Objekt mit der ID der angegebenen Session aus der Liste
	 * der eingeloggten User. Ist die Session unbenannt, so wird nichts
	 * entfernt.
	 * 
	 * @param session
	 *            Session des zu loeschenden <code>User</code>-Objekts
	 * 
	 */
	static void removeUserBySession(HttpSession session) {

	}
}