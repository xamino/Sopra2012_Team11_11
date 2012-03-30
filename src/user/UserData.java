package user;

import javax.servlet.http.HttpSession;

/**
 * Datenklasse für die Daten eines Benutzers.
 * 
 * @author Guentzel
 * 
 */
public class UserData {

	public UserData(String username, String email, String name,
			HttpSession session) {
		this.username = username;
		this.email = email;
		this.name = name;
		this.session = session;
	}

	private String name;
	/**
	 * Benutzername des Benutzers
	 */
	private String username;
	/**
	 * E-Mail Adresse des Benutzers
	 */
	private String email;
	/**
	 * Session des Benutzers
	 */
	private HttpSession session;

	/**
	 * Gibt den Realnamen zurueck
	 * 
	 * @return Realname
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setz den Realnamen
	 * 
	 * @param name
	 *            Gewuenschter Realname
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Benutzernamen zurueck
	 * 
	 * @return Benutzername
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setzt den Benutzernamen
	 * 
	 * @param username
	 *            Gewuenschter Benutzername
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gibt die Emailadresse des Benutzers zurueck
	 * 
	 * @return Emailadresse des Benutzers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzt die E-Mailadresse
	 * 
	 * @param email
	 *            Gewuenschte E-Mailadresse
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gibt die Session des Benutzers zurueck
	 * 
	 * @return Session des Benutzers
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * Setzt die Session des Benutzers
	 * 
	 * @param session
	 *            Gewuenschte Session
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}
}
