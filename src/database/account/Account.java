package database.account;

/**
 * Datenklasse fuer Accounts.
 */
public class Account {

	/**
	 * Der Benutzername des Benutzers.
	 */
	private String 	benutzername;
	/**
	 * Passworthash des Benutzerpassworts (je nach Verwendung auch Passwort im Klartext!).
	 */
	private String 	passworthash;
	/**
	 * Der Berechtigungstyp des Accounts. 
	 * 0 - Admin
	 * 1 - Anbieter
	 * 2 - Verwalter
	 * 3 - Bewerber
	 */
	private int		accounttyp;
	/**
	 * Email Adresse des Benutzers.
	 */
	private String 	email;
	/**
	 * Realer Name des Benutzers. Wird fuer Bewerbungsvorgaenge benoetigt.
	 * 
	 */
	private String	Name;
	
	/**
	 * Konstruktor
	 * 
	 * @param benutzername Benutzername im System.
	 * @param passworthash Passwort (Je nach verwendung Klartext oder schon Hash).
	 * @param accounttyp Accounttyp:
	 * 		0: Admin
	 * 		1: Anbieter
	 * 		2: Verwalter
	 * 		3: Bewerber
	 * @param email E-Mail adresse.
	 * @param name Realer Name des Accountinhabers.
	 */
	public Account(String benutzername, String passworthash, int accounttyp,
			String email, String name) {
		super();
		this.benutzername = benutzername;
		this.passworthash = passworthash;
		this.accounttyp = accounttyp;
		this.email = email;
		Name = name;
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
	 * @param benutzername Gewuenschter Benutzername.
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
	 * @param passworthash Gewuenschter Passworthash.
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
	 * @param accounttyp Gewuenschter Accounttyp
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
	 * @param email Gewuenschte Emailadresse.
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
		return Name;
	}
	/**
	 * Setzt den Namen.
	 * 
	 * @param name Gewuenschter Name.
	 */
	public void setName(String name) {
		Name = name;
	}
	
}
