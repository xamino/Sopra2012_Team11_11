/**
 * @author Patryk Boczon
 */
package database.application;
/**
 * Datenklasse fuer die Bewerbung.
 */
public class Application {

	/**
	 * Der Benutzername des Bewerbers.
	 */
	private String username;
	
	/**
	 * Die id der Bewerbung.
	 */
	private int aid;
	
	/**
	 * Der Status der Bewerbung.
	 */
	private boolean status;
	
	/**
	 * Der zustaendige Sachbearbeiter der Bewerbung.
	 */
	private String clerk;
	
	/**
	 * Parameter speichert akzeptiert (true) oder abgelehnt (false).
	 */
	private boolean chosen;
	
	

	public Application(String username, int aid, boolean status, String clerk,
			boolean chosen) {
		super();
		this.username = username;
		this.aid = aid;
		this.status = status;
		this.clerk = clerk;
		this.chosen = chosen;
	}
	

	/**
	 * Gibt den Benutzernamen zurueck.
	 * 
	 * @return Benutzername der Bewerbung.
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gibt die id der Bewerbung zurueck.
	 * 
	 * @return id der Bewerbung.
	 */
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * Gibt den Status (geprueft oder ungeprueft) der Bewerbung zurueck.
	 * 
	 * @return true falls geprueft, false falls ungeprueft.
	 */
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Gibt den Sachbearbeiter der Bewerbung zurueck.
	 * 
	 * @return Sachbearbeiter der Bewerbung.
	 */
	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}

	/**
	 * Gibt an, ob die Bewerbung akzeptiert wurde oder nicht.
	 * 
	 * @return true falls akzeptiert, false falls abgelehnt.
	 */
	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}
}
