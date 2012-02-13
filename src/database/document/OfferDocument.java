package database.document;

/**
 * Klasse fuer Standarddokumente für Angebote.
 * 
 * @author Guentzel
 * 
 */
public class OfferDocument {

	/**
	 * AngebotsID
	 */
	private int offerID;
	/**
	 * Document ID
	 */
	private int documentID;
	/**
	 * StandardKonstruktor
	 * @param offerID AngebotsID
	 * @param documentID UnterlagenID
	 */
	public OfferDocument(int offerID, int documentID) {
		super();
		this.offerID = offerID;
		this.documentID = documentID;
	}
	/**
	 * Gibt die AngebotsID zurueck
	 * @return AngebotsID
	 */
	public int getOfferID() {
		return offerID;
	}
	/**
	 * Setzt die AngebotsID
	 * @param offerID AngebotsID
	 */
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	/**
	 * Gibt die UnterlagenID zurueck
	 * @return UnterlagenID
	 */
	public int getDocumentid() {
		return documentID;
	}
	/**
	 *  Setzt die UnterlagenID
	 * @param documentID UnterlagenID
	 */
	public void setDocumentid(int documentID) {
		this.documentID = documentID;
	}

	
}
