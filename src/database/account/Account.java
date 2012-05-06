/**
 * @author Manuel Guentzel
 * @author Oemer Sahin
 * @author Patryk Boczon
 */
package database.account;

/**
 * Datenklasse fuer Accounts.
 */
public class Account {

	/**
	 * Der Benutzername des Benutzers.
	 */
	private String username;
	/**
	 * Passworthash des Benutzerpassworts. Achtung: ist <code>transient</code>
	 * um Passworthash vor automatiesierten Reflections zu schuetzen. Dies
	 * verhindert das Senden des Passworthashs durch GSON im AdminServlet.
	 */
	// Kommentar von Tamino: NICHT transient wegmachen – sollte das gebraucht
	// werden MUSS man das mit mir klären da sonst eine riesen Sicherheitslücke
	// im System entsteht!
	private transient String passwordhash;
	/**
	 * Der Berechtigungstyp des Accounts. 0 - Admin 1 - Anbieter 2 - Verwalter 3
	 * - Bewerber
	 */
	private int accounttype;
	// Constants for easy access:
	public static final int ADMIN = 0;
	public static final int ANBIETER = 1;
	public static final int VERWALTER = 2;
	public static final int BEWERBER = 3;
	/**
	 * Email Adresse des Benutzers.
	 */
	private String email;
	/**
	 * Realer Name des Benutzers. Wird fuer Bewerbungsvorgaenge benoetigt.
	 * 
	 */
	private String name;
	/**
	 * Institutszugehoerigkeit (nur relevant fuer Anbieter und Verwalter).
	 */
	private int institute;
	/**
	 * Repraesentant
	 */
	private String representative;

	/**
	 * Konstruktor
	 * 
	 * @param username
	 *            Benutzername im System.
	 * @param passwordhash
	 *            Passwort (Je nach verwendung Klartext oder schon Hash).
	 * @param accounttype
	 *            Accounttyp: 0: Admin 1: Anbieter 2: Verwalter 3: Bewerber.
	 * @param email
	 *            E-Mail adresse.
	 * @param name
	 *            Realer Name des Accountinhabers.
	 * @param institute
	 * @param representative
	 */
	public Account(String username, String passwordhash, int accounttype,
			String email, String name, int institute, String representative) {
		super();
		this.username = username;
		this.passwordhash = passwordhash;
		this.accounttype = accounttype;
		this.email = email;
		this.name = name;
		this.institute = institute;
		this.representative = representative;
	}

	/**
	 * Gibt die Institut ID zurueck.
	 * 
	 * @return Institut ID
	 */
	public int getInstitute() {
		return institute;
	}

	/**
	 * Setzt die InstitutID auf den gewuenschten Wert.
	 * 
	 * @param institute
	 *            gewuenschte InstitutID.
	 */
	public void setInstitute(int institute) {
		this.institute = institute;
	}

	/**
	 * Gibt den Benutzernamen zurueck.
	 * 
	 * @return Benutzername des Accountinhabers.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setzt den Benutzernamen.
	 * 
	 * @param username
	 *            Gewuenschter Benutzername.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gibt den Passworthash zurueck.
	 * 
	 * @return Passworthash des Passworts des Accountinhabers.
	 */
	public String getPasswordhash() {
		return passwordhash;
	}

	/**
	 * Setzt den Passworthash.
	 * 
	 * @param passwordhash
	 *            Gewuenschter Passworthash.
	 */
	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	/**
	 * Gibt den Accounttyp zurueck.
	 * 
	 * @return Accounttyp des Accountinhabers.
	 */
	public int getAccounttype() {
		return accounttype;
	}

	/**
	 * Setzt den Accounttyp.
	 * 
	 * @param accounttype
	 *            Gewuenschter Accounttyp.
	 */
	public void setAccounttype(int accounttype) {
		this.accounttype = accounttype;
	}

	/**
	 * Gibt die Emailadresse zurueck.
	 * 
	 * @return Emailadresse des Accountinhabers.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzt die Emailadresse.
	 * 
	 * @param email
	 *            Gewuenschte Emailadresse.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gibt den Namen zurueck.
	 * 
	 * @return Name des Accountinhabers.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen.
	 * 
	 * @param name
	 *            Gewuenschter Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Stellvertreternamen zurueck.
	 * 
	 * @return Der Stellvertretername.
	 */
	public String getRepresentative() {
		return representative;
	}

	/**
	 * Aendert den Stellvertreter des Mitarbeiter.
	 * 
	 * @param representative
	 */
	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	/**
	 * Einfache toString methode. Gibt nicht alle Parameter zurueck.
	 */
	public String toString() {
		return "[" + username + ":" + name + ":" + email + "]";
	}
}
