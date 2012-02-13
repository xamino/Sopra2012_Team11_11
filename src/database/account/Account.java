/**
 * @author Manuel Guentzel
 * @author Oemer Sahin
 */
package database.account;

/**
 * Datenklasse fuer Accounts.
 */
public class Account {

	/**
	 * Der Benutzername des Benutzers.
	 */
	private String benutzername;
	/**
	 * Passworthash des Benutzerpassworts (je nach Verwendung auch Passwort im
	 * Klartext!).
	 */
	private String passworthash;
	/**
	 * Der Berechtigungstyp des Accounts. 0 - Admin 1 - Anbieter 2 - Verwalter 3
	 * - Bewerber
	 */
	private int accounttyp;
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
	 * Institutszugehoerigkeit (nur relevant fuer Anbieter und Verwalter)
	 */
	private int institute;

	private String representative;

	/**
	 * Konstruktor
	 * 
	 * @param benutzername
	 *            Benutzername im System.
	 * @param passworthash
	 *            Passwort (Je nach verwendung Klartext oder schon Hash).
	 * @param accounttyp
	 *            Accounttyp: 0: Admin 1: Anbieter 2: Verwalter 3: Bewerber
	 * @param email
	 *            E-Mail adresse.
	 * @param name
	 *            Realer Name des Accountinhabers.
	 */
	public Account(String benutzername, String passworthash, int accounttyp,
			String email, String name, int institute, String representative) {
		super();
		this.benutzername = benutzername;
		this.passworthash = passworthash;
		this.accounttyp = accounttyp;
		this.email = email;
		this.name = name;
		this.institute = institute;
		this.representative = representative;
	}

	/**
	 * Gibt die Institut ID zurueck
	 * 
	 * @return Institut ID
	 */
	public int getInstitute() {
		return institute;
	}

	/**
	 * Setzt die InstitutID auf den gewuenschten Wert
	 * 
	 * @param institute
	 *            gewuenschte InstitutID
	 */
	public void setInstitute(int institute) {
		this.institute = institute;
	}

	/**
	 * Gibt den Benutzernamen zurueck.
	 * 
	 * @return Benutzername des Accountinhabers.
	 */
	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * Setzt den Benutzernamen.
	 * 
	 * @param benutzername
	 *            Gewuenschter Benutzername.
	 */
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	/**
	 * Gibt den Passworthash zurueck.
	 * 
	 * @return Passworthash des Passworts des Accountinhabers.
	 */
	public String getPassworthash() {
		return passworthash;
	}

	/**
	 * Setzt den Passworthash.
	 * 
	 * @param passworthash
	 *            Gewuenschter Passworthash.
	 */
	public void setPassworthash(String passworthash) {
		this.passworthash = passworthash;
	}

	/**
	 * Gibt den Accounttyp zurueck.
	 * 
	 * @return Accounttyp des Accountinhabers.
	 */
	public int getAccounttyp() {
		return accounttyp;
	}

	/**
	 * Setzt den Accounttyp
	 * 
	 * @param accounttyp
	 *            Gewuenschter Accounttyp
	 */
	public void setAccounttyp(int accounttyp) {
		this.accounttyp = accounttyp;
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
	 * @return
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

}
