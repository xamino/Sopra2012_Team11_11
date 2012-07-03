/**
 * @author Patryk Boczon
 * 
 */
package database.institute;

public class Institute {
	/**
	 * Die ID des Institutes.
	 */
	private int IID;
	/**
	 * Der Name des Institutes.
	 */
	private String name;
	
	public Institute(int IID, String name) {
		this.IID = IID;
		this.name = name;
	}

	/**
	 * Gibt die IID zur�ck.
	 * 
	 * @return die IID
	 */
	public int getIID() {
		return IID;
	}

	/**
	 * Setzt die IID.
	 * 
	 * @param IID
	 *            die zu setzende IID
	 */
	public void setIID(int iID) {
		IID = iID;
	}

	/**
	 * Gibt den Name zur�ck.
	 * 
	 * @return der name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen.
	 * 
	 * @param name
	 *            Der zu setzende Name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
