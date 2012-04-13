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
	 * @return the iID
	 */
	public int getIID() {
		return IID;
	}

	/**
	 * @param iID
	 *            the iID to set
	 */
	public void setIID(int iID) {
		IID = iID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
