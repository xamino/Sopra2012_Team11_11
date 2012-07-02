/**
 *  @author Patryk Boczon
 */

package database;

/**
 * Hilfsklasse zur Speicherung eines Datensatzes einer Datenbankabfrage. Wird
 * vom Clerk, zur Anzeige von angenommenen Bewerbungen eines bestimmten
 * Instituts, benoetigt.
 */
public class HilfsDatenClerk {

	/**
	 * Name des Bewerbers
	 */
	private String bewerbername;
	/**
	 * Name des Angebots
	 */
	private String angebotsname;

	/**
	 * Username des Bewerbers
	 */
	private String username;

	/**
	 * aid des Angebots
	 */
	private int aid;

	/**
	 * Konstruktor fuer den Datensatz
	 * 
	 * @param bewerbername
	 *            Uebergibt den Bewerbernamen
	 * @param angebotsname
	 *            Uebergibt den Angebotsnamen
	 * @param username
	 *            Uebergibt den Username des Bewerbes
	 * @param aid
	 *            Uebergibt die aid des Angebots
	 * 
	 */
	public HilfsDatenClerk(String bewerbername, String angebotsname,
			String username, int aid) {
		this.bewerbername = bewerbername;
		this.angebotsname = angebotsname;
		this.username = username;
		this.aid = aid;

	}

	/**
	 * Gibt den Bewerbernamen zurueck.
	 * @return Bewerbername
	 */
	public String getBewerbername() {
		return bewerbername;
	}

	/**
	 * Setzt den Bewerbername.
	 * 
	 * @param bewerbername
	 *            zu setzender Bewerbername.
	 */
	public void setBewerbername(String bewerbername) {
		this.bewerbername = bewerbername;
	}

	/**
	 * Gibt den Angebotsnamen zurueck
	 * @return Angebotsname
	 */
	public String getAngebotsname() {
		return angebotsname;
	}

	/**
	 * Setzt den Angebootsnamen.
	 * @param angebotsname
	 *            Zu setzender Angebotsname.
	 */
	public void setAngebotsname(String angebotsname) {
		this.angebotsname = angebotsname;
	}

	/**
	 * Gibt den Benutzernamen zurueck.
	 * @return Benutzername
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setzt den Benutzername.
	 * @param username
	 *            Zu setzender Benutzername.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gibt die AngebotsId zurueck.
	 * @return AngebotsId
	 */
	public int getAid() {
		return aid;
	}

	/**
	 * Setzt die AngebotsId
	 * @param aid
	 *            Zu setzende AngebotsId.
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}
}
