/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 */

package database.document;

/**
 * Verwaltet alle Datenbankzugriffe auf Unterlagen-bezogene Daten.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseController;
import database.account.Account;
import database.offer.Offer;

public class DocumentController {

	public DatabaseController dbc;

	/**
	 * Beinhaltet die ApplicationController-Instanz. Diese wird, falls keine
	 * vorhanden war, mit der Methode <code>getInstance</code> angelegt.
	 */
	private static DocumentController doccontr;

	/**
	 * Diese Methode prueft ob ein DocumentController-Objekt existiert. Falls
	 * nicht wird eine neue DocumentController-Instanz angelegt, zurueckgegeben
	 * und in dem Klassenattribut <code>doccontr</code> abgespeichert. Dies
	 * dient zur Gewaehrleistung, dass nur eine Instanz von DocumentController
	 * existiert.
	 * 
	 * @return Es wird eine Instanz zurueckgegeben.
	 */
	public static DocumentController getInstance() {
		if (doccontr == null)
			doccontr = new DocumentController();
		return doccontr;
	}

	/**
	 * Privater Konstruktor, da die Klasse ein Singleton ist. Dieser Konstruktor
	 * wird ueber <code>getInstance()</code> aufgerufen.
	 */
	private DocumentController() {

	}

	/**
	 * Diese Methode erstellt eine Unterlage des Administrators in der
	 * Datenbank. Mit uebergebenem Unterlagen-Objekt (Document-Objekt).
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 */
	public void createDocument(Document document) {
		
		dbc.insert("Unterlagen",new Object[]{document.getUid(),document.getName(),document.getDescription()});
	}
	
	/**
	 * Diese Methode loescht eine Administrator Unterlage aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 */
	public void deleteDocument(Document document) {
		/*Beim Loeschen einer Unterlage muss diese nicht nur in "Unterlagen", sondern auch in "Standardunterlagen"
		 * und "Bewerbungsunterlagen" geloescht werden, da diese nicht mehr existiert.
		 */
		dbc.delete("Berwerbungsunterlagen", "UID="+document.getUid());
		dbc.delete("Standardunterlagen", "UID="+document.getUid());
		dbc.delete("Unterlagen", "UID="+document.getUid());
	}

	/**
	 * Diese Methode aendert die Attribute einer Administrator Unterlage bzw.
	 * aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 */
	public void updateDocument(Document document) {
		/* !!! Eine abgeaenderte Unterlage benoetigt neue Parameter. Wo werden diese uebergeben?
		 * Parameter erweitern?:
		 *  public void updateDocument(Document document, String name, String description){
		 *  dbc.update("Unterlagen", new String[] {"Name","Beschreibung"}, new Object[]{document.setName(name),document.setDescription(description)}, "UID="+document.getUid());
		 *	Oder Uebergabe eines komplett neuen Unterlagen-(/Document-) Objekts?
		 */
		dbc.update("Unterlagen", new String[] {"Name","Beschreibung"}, new Object[]{document.getName(),document.getDescription()}, "UID="+document.getUid());
	}

	/**
	 * Diese Methode sammelt alle Administrator Unterlagen zu einem bestimmten
	 * Jobangebot aus der Datenbank und speichert diese in einem Vector.
	 * 
	 * @param aid
	 *            Parameter <code>aid</code> (Angebots-Id) ist die Id des
	 *            Jobangebots.
	 * @return Es wird ein Vector mit allen Unterlagen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<Document> getDocumentsByOffer(int aid) {
		
		//Vector fuer die Rueckgabe der Unterlagen eines bestimmten Angebots bei gegebener Angebots-ID
		Vector<Document> docVect= new Vector<Document>();
		ResultSet rs = dbc.select(new String[]{"UID"}, new String[]{"Standardunterlagen"}, "AID="+aid);
		
		try {
			while(rs.next())
			{
				docVect.add((Document)rs.getObject(1)); // !!! Wie aus einem ResultSet ein Document-Objekt machen?  !!!
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
        try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}// noetig?
		return docVect;
	}
	
	/**
	 * Diese Methode sammelt alle Bewerber-Unterlagen zu einem bestimmten Jobangebot aus
	 * der Datenbank und speichert diese in einem Vector.
	 * 
	 * @param aid
	 *            Parameter "aid" (Angebots-Id) ist die Id des Jobangebots.
	 * @return Es wird ein Vector mit allen Unterlagen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<AppDocument> getAppDocumentByOffer(int aid){
		
		//Vector fuer die Rueckgabe der Bewerbungsunterlagen eines bestimmten Angebots bei gegebener Angebots-ID
		Vector<AppDocument> appDocVect= new Vector<AppDocument>();
		ResultSet rs = dbc.select(new String[]{"UID"}, new String[]{"Bewerbungsunterlagen"}, "AID="+aid);
		
		try {
			while(rs.next())
			{
				appDocVect.add((AppDocument)rs.getObject(1)); // !!! Wie aus einem ResultSet ein Document-Objekt machen?  !!!
			        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		} // noetig?
		return appDocVect;
		
	}

	/**
	 * Diese Methode sammelt alle noetigen Unterlagen von einem Bewerber zu
	 * einem Jobangebot.
	 * 
	 * @param account
	 *            Parameter <code>account</code> ist ein Account-Objekt mit
	 *            allen Account-Attributen.
	 * @param offer
	 *            Parameter <code>offer</code> ist ein Offer-Objekt mit allen
	 *            Offer-Attributen.
	 * @return Es wird ein Vector mit allen Unterlagen von einem bestimmten
	 *         Account zu einem bestimmten Jobangebot aus der Datenbank
	 *         zurueckgegeben.
	 */
	public Vector<Document> getDocumentsByUserAndOffer(Account account, Offer offer) {
		// Account oder Application Instanz?
		
		//Vector fuer die Rueckgabe der Unterlagen eines bestimmten Angebots und Accounts bei gegebenem Account und Angebot
		Vector<Document> userOffDocVect= new Vector<Document>();
		ResultSet rs = dbc.select(new String[]{"UID"}, new String[]{"Bewerbungsunterlagen"}, "Benutzername="+account+" AND AID="+offer.getAid());
		
		try {
			while(rs.next())
			{
				userOffDocVect.add((Document)rs.getObject(1)); // !!! Wie aus einem ResultSet ein Document-Objekt machen?  !!!
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
        try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}// noetig?
		return userOffDocVect;
	}

	/**
	 * Diese Methode sammelt alle Unterlagen aus der Datenbank und speichert
	 * diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen vorhanden Unterlagen aus der
	 *         Datenbank zurueckgegeben.
	 * @throws SQLException 
	 */
	public Vector<Document> getAllDocuments() {

		//Vector fuer die Rueckgabe aller vorhandenen Unterlagen 
		Vector<Document> allDocVect= new Vector<Document>();
		ResultSet rs = dbc.select(new String[]{"*"}, new String[]{"Unterlagen"}, "");
		
		try {
			while(rs.next())
			{
				allDocVect.add((Document) rs.getObject(1)); // !!! Wie aus einem ResultSet ein Document-Objekt machen?  !!!
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
        try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}// noetig?
		return allDocVect;
	}

	/**
	 * Diese Methode erstellt ein Applikationsunterlagen-Objekt
	 * (ApplicationDocument-Objekt) in der Datenbank. Mit uebergebenem
	 * Applikationsunterlagen-Objekt (ApplicationDocument-Objekt).
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein AppDocument-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 */
	public void createAppDocument(AppDocument document) {
		dbc.insert("Bewerbungsunterlagen",new Object[]{document.getUsername(),document.getoID(),document.getdID(), document.getPresent()});
	}

	/**
	 * Diese Methode loescht ein Applikationsunterlagen-Objekt
	 * (AppDocument-Objekt) aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Applikationsdokument-Objekt mit allen dazugehoerigen
	 *            Attributen.
	 */
	public void deleteAppDocument(AppDocument document) {
		dbc.delete("Bewerbungsunterlagen", "Benutzername="+document.getUsername()+" AND AID="+document.getoID()+" AND UID="+document.getdID());
	}

	/**
	 * Diese Methode aendert die Attribute einer ApplikationsUnterlage
	 * beziehungsweise aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Applikationsdokument-Objekt mit allen dazugehoerigen
	 *            Attributen.
	 */
	public void updateAppDocument(AppDocument document) {
			dbc.update("Bewerbungsunterlagen", new String[]{"Benutzername","AID", "UID", "Status"}, new String[]{document.getUsername(),String.valueOf(document.getoID()),String.valueOf(document.getdID()),
						String.valueOf(document.getPresent())}, "");
	}

	/**
	 * Diese Methode erstellt ein Angebotsunterlagen-Objekt
	 * (OfferDocument-Objekt) in der Datenbank. Mit ubergebenem
	 * Angebotsunterlagen-Objekt (OfferDocument-Objekt).
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Angebotsdokument-Objekt mit allen dazugehoerigen Attributen.
	 */
	public void createOfferDocument(OfferDocument document) {
		dbc.insert("Standardunterlagen",new Object[]{document.getOfferID(),document.getDocumentid()});
	}

	/**
	 * Diese Methode loescht ein Angebotunterlagen-Objekt (OfferDocument-Objekt)
	 * aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein AngebotDokument-Objekt
	 *            mit allen dazugehoerigen Attributen.
	 */
	public void deleteOfferDocument(OfferDocument document) {
		dbc.delete("Standardunterlagen", "AID="+document.getOfferID()+" AND UID="+document.getDocumentid());
	}

	/**
	 * Diese Methode aendert die Attribute einer Angebotsunterlage
	 * beziehungsweise aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Angebotsdokument-Objekt mit allen dazugehoerigen Attributen.
	 */
	public void updateOfferDocument(OfferDocument document) {
		dbc.update("Standardunterlagen", new String[]{"AID","UID"}, new Object[]{document.getOfferID(),document.getDocumentid()}, "AID="+document.getOfferID()+" AND UID="+document.getDocumentid());
	}

	/**
	 * Diese Methode dient dazu, den Status der eingesehenen Bewerbungsunterlagen anzugeben?
	 */
	public void updateStatus() {
		
	}
}
