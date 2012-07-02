/**
 * @author Manuel Guentzel
 * @author Anatoli Brill
 */

package database.document;

/**
 * Klasse fuer Bewerbungsdokumente. Diese Dokumente dienen der Verfolgung von
 * Dokumenten, welche der Bewerber dem Sachbearbeiter zukommen lassen muss.
 */
public class AppDocument {

	/**
	 * Der Benutzername vom Benutzer.
	 */
	private String username;
	/**
	 * Die ID des Angebots.
	 */
	private int offerID;
	/**
	 * Die ID der Unterlage.
	 */
	private int docID;
	/**
	 * Falls die Unterlage abgegeben wurde, wird es auf <code>TRUE</code>
	 * gesetzt. Ansonsten auf <code>FALSE</code>.
	 */
	private boolean present;

	/**
	 * Konstruktor fuer die Bewerbungsunterlagen.
	 * 
	 * @param username
	 *            Benutzername.
	 * @param aID
	 *            Angebots ID.
	 * @param uID
	 *            Unterlagen ID.
	 * @param present
	 *            Falls Unterlage vorhanden <code>TRUE</code> ansonsten
	 *            <code>FALSE</code>.
	 */
	public AppDocument(String username, int aID, int uID, boolean present) {
		super();
		this.username = username;
		this.offerID = aID;
		this.docID = uID;
		this.present = present;
	}

	/**
	 * Holt den Benutzernamen.
	 * 
	 * @return Benutzername.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setzt den Benutzername.
	 * 
	 * @param username
	 *            .
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Holt die AngebotsID.
	 * 
	 * @return AngebotsID.
	 */
	public int getoID() {
		return offerID;
	}

	/**
	 * Setzt die AngebotsID.
	 * 
	 * @param oID
	 *            .
	 */
	public void setoID(int oID) {
		this.offerID = oID;
	}

	/**
	 * Holt die UnterlagenID.
	 * 
	 * @return UnterlagenID.
	 */
	public int getdID() {
		return docID;
	}

	/**
	 * Setzt die UnterlagenID.
	 * 
	 * @param dID
	 *            .
	 */
	public void setdID(int dID) {
		this.docID = dID;
	}

	/**
	 * Holt die Information ob die Unterlage vorhanden ist.
	 * 
	 * @return <code>TRUE</code> Unterlage vorhanden, sonst <code>FALSE</code>.
	 */
	public boolean getPresent() {
		return present;
	}

	/**
	 * Setzt die Information, ob die Unterlage vorhanden ist.
	 * 
	 * @param present
	 *            <code>TRUE</code> Unterlage vorhanden, sonst
	 *            <code>FALSE</code>.
	 */
	public void setPresent(boolean present) {
		this.present = present;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppDocument [username=" + username + ", offerID=" + offerID
				+ ", docID=" + docID + ", present=" + present + "]";
	}

}
