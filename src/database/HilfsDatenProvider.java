/**
 *  @author Patryk Boczon
 */

package database;

/**
 * Hilfsklasse zur Speicherung eines Datensatzes. 
 * Wird vom Provider zur Anzeige von Bewerbungen eines bestimmten
 * Angebots benoetigt.
 */

public class HilfsDatenProvider {

	/**
	 * Name des Bewerbers
	 */
	private String name;
	
	/**
	 * Benutzername des Bewerbers
	 */
	private String username;
	
	/**
	 * E-Mail des Bewerbers
	 */
	private String email;
	
	/**
	 * Angenommen- oder nicht-angenommen-Status der Bewerbung
	 */
	private String angenommen;

	
	/**
	 * Konstruktor fuer den Datensatz
	 * 
	 * @param name
	 * Name des Bewerbers
	 * @param username
	 * Benutzername des Bewerbers
	 * @param email
	 * E-Mail des Bewerbers
	 * @param angenommen
	 * Angenommen- oder nicht-angenommen-Status der Bewerbung
	 */
	public HilfsDatenProvider(String name, String username, String email,
			String angenommen) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.angenommen = angenommen;
	}

	/**
	 * Gibt den Namen des Bewerbers zurueck
	 * @return Name des Bewerbers
	 */
	public String getName() {
		return name;
	}

	/**
	 * Aendern den Namen des Bewerbers
	 * @param name
	 * Name des Bewerbers
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Benutzernamen des Bewerbers zurueck
	 * @return
	 * Benutzername des Bewerbers
	 */		
	public String getUsername() {
		return username;
	}

	/**
	 * Aendert den Benutzernamen des Bewerbers
	 * @param username
	 * Benutzername des Bewerbers
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gibt die E-Mail des Bewerbers zurueck
	 * @return
	 * E-Mail des Bewerbers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Aendert die E-Mail des Bewerbers
	 * @param email
	 * E-Mail des Bewerbers
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gibt den Status (angenommen oder nicht angenommen) der Bewerbung zurueck
	 * @return
	 * Status der Bewerbung
	 */
	public String getAngenommen() {
		return angenommen;
	}

	/**
	 * Aendert den Status (angenommen oder nicht angenommen) der Bewerbung
	 * @param angenommen
	 * Status der Bewerbung
	 */
	public void setAngenommen(String angenommen) {
		this.angenommen = angenommen;
	}
}
