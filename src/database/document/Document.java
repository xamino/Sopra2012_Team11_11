/**
 * @author Patryk Boczon
 * @author Manuel Guentzel
 */

package database.document;

/**
 * Datenklasse fuer die Dokumente welche der Admin verwaltet. Diese Dokumente
 * stehen allen Sachbearbeitern zur Verfuegung, um die Liste der benoetigten
 * Dokumente zu einem Angebot zu erstellen.
 */
public class Document {
	
	/**
	 * Die id des Dokuments.
	 */
	private int uid;
	
	/**
	 * Der Name des Dokuments.
	 */
	private String name;

	/**
	 * Die Beschreibung des Dokuments.
	 */
	private String description;

	/**
	 * Konstruktor
	 * 
	 * @param uid
	 * 		Die id des Dokuments.
	 * @param name
	 * 		Der Name des Dokuments.
	 * @param description
	 * 		Die Beschreibung des Dokuments.
	 */
	public Document(int uid, String name, String description) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Gibt die id des Dokuments zurueck.
	 * 
	 * @return Dokument ID
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * Setzt die id des Dokuments.
	 * 
	 * @param uid
	 *            Gewuenschte id fuer das Dokument.
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * Gibt den Namen des Dokuments zurueck.
	 * 
	 * @return Dokumentenname
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Dokumentennamen.
	 * 
	 * @param name
	 *            Gewuenschter Dokumentenname.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt die Beschreibung des Dokuments zurueck.
	 * 
	 * @return Dokumentenbeschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setzt die Dokumentenbeschreibung.
	 * 
	 * @param description
	 *            Gewuenschte Dokumentenbeschreibung.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Document [uid=" + uid + ", name=" + name + ", description="
				+ description + "]";
	}
}
