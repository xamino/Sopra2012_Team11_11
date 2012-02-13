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
	private boolean finished;
	
	/**
	 * Der zustaendige Sachbearbeiter der Bewerbung.
	 */
	private String clerk;
	
	/**
	 * Parameter speichert akzeptiert (true) oder abgelehnt (false).
	 */
	private boolean chosen;
	
	
	/**
	 * Konstruktor
	 * 
	 * @param username
	 * 		Der Benutzername des Bewerbers.
	 * @param aid
	 * 		Die id der Bewerbung.
	 * @param status
	 * 		Der Status der Bewerbung.
	 * @param clerk
	 * 		Der zustaendige Sachbearbeiter der Bewerbung.
	 * @param chosen
	 * 		Parameter speichert akzeptiert (true) oder abgelehnt (false).
	 */
	public Application(String username, int aid, boolean status, String clerk,
			boolean chosen) {
		super();
		this.username = username;
		this.aid = aid;
		this.finished = status;
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

	/**
	 * Setzt den Benutzernamen.
	 * 
	 * @param benutzername
	 *            Gewuenschter Benutzername.
	 */
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

	/**
	 * Setzt die Bewerbungsid.
	 * 
	 * @param aid
	 *            Gewuenschte Bewerbungsid.
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * Gibt den zurueck ob die Bewerbung abgeschlossen ist.
	 * 
	 * @return true falls abgeschlossen, false falls noch laufend.
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Setzt den Bewerbungsstatus.
	 * 
	 * @param status
	 *            Gewuenschter Bewerbungsstatus.
	 */
	public void setFinished(boolean status) {
		this.finished = status;
	}

	/**
	 * Gibt den Sachbearbeiter der Bewerbung zurueck.
	 * 
	 * @return Sachbearbeiter der Bewerbung.
	 */
	public String getClerk() {
		return clerk;
	}

	/**
	 * Setzt den Sachbearbeiter fuer diese Bewerbung.
	 * 
	 * @param clerk
	 *            Gewuenschte Sachbearbeiter.
	 */
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

	/**
	 * Setzt den Bewerbungsstatus (angenommen oder abgelehnt).
	 * 
	 * @param chosen
	 *            Gewuenschter Bewerbungsstatus (angenommen oder abgelehnt).
	 */
	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}
}
