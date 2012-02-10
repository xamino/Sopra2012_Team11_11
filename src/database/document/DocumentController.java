/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 */

package database.document;
/**
 * Verwaltet alle Datenbankzugriffe auf Unterlagen-bezogene Daten
 */
import java.util.Vector;

import database.DatabaseController;
import database.account.Account;
import database.application.ApplicationController;
import database.offer.Offer;

public class DocumentController {
	
	public DatabaseController dbc;
	
	/**
	 * Klassenattribut "doccontr" beinhaltet eine ApplicationController-Instanz,
	 * falls keine vorhanden war und mit der Methode getInstance angelegt wird.
	 * Dies dient um zu gewaehrleisten, dass nur eine Instanz von
	 * ApplicationController existiert.
	 */
	private static DocumentController doccontr;
	
	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private DocumentController(){
		
	}
		
	/**
	 * Diese Methode erstellt ein Unterlagen-Objekt (Document-Objekt) in der Datenbank. Mit
	 * ubergebenem Unterlagen-Objekt (Document-Objekt).
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void createDocument(Document document){
		
	}
	
	/**
	 * Diese Methode loescht ein Unterlagen-Objekt (Document-Objekt) aus der Datenbank.
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void deleteDocument(Document document){
		
	}
	
	/**
	 * Diese Methode aendert die Attribute einer Unterlage bzw. aktualisiert diese in der Datenbank.
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateDocument(Document document){
		
	}
	
	/**
	 * Diese Methode sammelt alle Unterlagen zu einem bestimmten Jobangebot aus
	 * der Datenbank und speichert diese in einem Vektor.
	 * 
	 * @param aid
	 *            Parameter "aid" (Angebots-Id) ist die Id des Jobangebots.
	 * @return Es wird ein Vektor mit allen Unterlagen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<Document> getDocumentsByOffer(int aid){
		return null;
		
	}
	
	/**
	 * Diese Methode sammelt alle Unterlagen von einem bestimmten Account zu einem bestimmten Jobangebot.
	 * @param account
	 * 	Parameter "account" ist ein Account-Objekt mit allen Account-Attributen.
	 * @param offer
	 * 	Parameter "offer" ist ein Offer-Objekt mit allen Offer-Attributen.
	 * @return
	 * Es wird ein Vektor mit allen Unterlagen von einem bestimmten Account zu einem bestimmten Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<Document> getDocumentsByUserAndOffer(Account account, Offer offer){
		return null;
		
	}
	
	/**
	 * Diese Methode sammelt alle Unterlagen aus der Datenbank und speichert
	 * diese in einem Vektor.
	 * 
	 * @return Es wird ein Vektor mit allen vorhanden Unterlagen aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Document> getAllDocuments(){
		return null;
		
	}
	
		
	/**
	 * Diese Methode prueft ob ein DocumentController-Objekt existiert. Falls
	 * nicht wird eine neue DocumentController-Instanz angelegt, zurueckgegeben
	 * und in dem Klassenattribut "doccontr" abgespeichert. Dies dient um zu
	 * gewaehrleisten, dass nur eine Instanz von DocumentController
	 * existiert.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static DocumentController getInstance() {
		if (doccontr == null)
			doccontr = new DocumentController();
		return doccontr;

	}
	//------------------ Ab hier Javadoc Kommentare abaendern! ----------------------------
	/**
	 * Diese Methode erstellt ein Unterlagen-Objekt (Document-Objekt) in der Datenbank. Mit
	 * ubergebenem Unterlagen-Objekt (Document-Objekt).
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void createApplicationDocument(AppDocument document){
		
	}
	
	/**
	 * Diese Methode loescht ein Unterlagen-Objekt (Document-Objekt) aus der Datenbank.
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void deleteApplicationDocument(AppDocument document){
		
	}
	
	/**
	 * Diese Methode aendert die Attribute einer Unterlage bzw. aktualisiert diese in der Datenbank.
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateApplicationDocument(AppDocument document){
		
	}
	
	/**
	 * Diese Methode erstellt ein Unterlagen-Objekt (Document-Objekt) in der Datenbank. Mit
	 * ubergebenem Unterlagen-Objekt (Document-Objekt).
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void createOfferDocument(OfferDocument document){
		
	}
	
	/**
	 * Diese Methode loescht ein Unterlagen-Objekt (Document-Objekt) aus der Datenbank.
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void deleteOfferDocument(OfferDocument document){
		
	}
	
	/**
	 * Diese Methode aendert die Attribute einer Unterlage bzw. aktualisiert diese in der Datenbank.
	 * @param document
	 * Parameter "document" ist ein Document-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateOfferDocument(OfferDocument document){
		
	}
	
}
