/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 * @author Manuel Güntzel
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
		dbc = DatabaseController.getInstance();
		logger.Log.getInstance().write("DocumentController",
				"Instance created.");
	}

	/**
	 * Diese Methode erstellt eine Unterlage des Administrators in der
	 * Datenbank. Mit uebergebenem Unterlagen-Objekt (Document-Objekt).
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 * @return Gibt an, ob das Document erstellt werden konnte.
	 */
	public boolean createDocument(Document document) { // checked
		return dbc.insert("Unterlagen", new Object[] { document.getUid(),
				document.getName(), document.getDescription() });
	}

	/**
	 * Diese Methode loescht eine Administrator Unterlage aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 */
	public void deleteDocument(Document document) { // checked
		/*
		 * Beim Loeschen einer Unterlage muss diese nicht nur in "Unterlagen",
		 * sondern auch in "Standardunterlagen" und "Bewerbungsunterlagen"
		 * geloescht werden, da diese nicht mehr existiert.
		 */
		dbc.delete("Bewerbungsunterlagen", "UID=" + document.getUid());
		dbc.delete("Standardunterlagen", "UID=" + document.getUid());
		dbc.delete("Unterlagen", "UID=" + document.getUid());
	}

	/**
	 * Diese Methode aendert die Attribute einer Administrator Unterlage bzw.
	 * aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 */
	public void updateDocument(Document document) { // checked
		dbc.update("Unterlagen", new String[] { "Name", "Beschreibung" },
				new Object[] { document.getName(), document.getDescription() },
				"UID=" + document.getUid());
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
	public Vector<OfferDocument> getDocumentsByOffer(int aid) { // checked

		// Vector fuer die Rueckgabe der Unterlagen eines bestimmten Angebots
		// bei gegebener Angebots-ID
		Vector<OfferDocument> docVect = new Vector<OfferDocument>();
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { "Standardunterlagen" }, "AID=" + aid);

		try {
			while (rs.next()) {
				docVect.add(new OfferDocument(rs.getInt("aid"), rs
						.getInt("uid"))); // !!! Mit ResultSet ein
				// Document-Objekt machen !!!
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}

		/*
		 * //Nur zum Testen, ob alle Standardunterlagen mit aid im Vector sind
		 * for (int i=0;i<docVect.size();i++){ try{ OfferDocument offDoc =
		 * docVect.elementAt(i);
		 * System.out.println("AID="+offDoc.getOfferID()+" UID="
		 * +offDoc.getDocumentid()); } catch (ArrayIndexOutOfBoundsException
		 * ae){
		 * System.out.println("Paramater ausserhalb des Bereichs vom Vector!");
		 * }
		 * 
		 * }
		 */

		return docVect;

	}

	/**
	 * Diese Methode sammelt alle Bewerber-Unterlagen zu einem bestimmten
	 * Jobangebot aus der Datenbank und speichert diese in einem Vector.
	 * 
	 * @param aid
	 *            Parameter "aid" (Angebots-Id) ist die Id des Jobangebots.
	 * @return Es wird ein Vector mit allen Unterlagen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<AppDocument> getAppDocumentByOffer(int aid) { // checked

		// Vector fuer die Rueckgabe der Bewerbungsunterlagen eines bestimmten
		// Angebots bei gegebener Angebots-ID
		Vector<AppDocument> appDocVect = new Vector<AppDocument>();
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { "Bewerbungsunterlagen" }, "AID=" + aid);

		try {
			while (rs.next()) {
				appDocVect.add(new AppDocument(rs.getString("benutzername"), rs
						.getInt("aid"), rs.getInt("uid"), rs
						.getBoolean("status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}
		/*
		 * //Nur zum Testen, ob alle Bewerbungsunterlagen mit aid im Vector sind
		 * for (int i=0;i<appDocVect.size();i++){ try{ AppDocument offDoc =
		 * appDocVect.elementAt(i);
		 * System.out.println("Benutzername="+offDoc.getUsername
		 * ()+"  AID="+offDoc
		 * .getoID()+" UID="+offDoc.getdID()+" Status="+offDoc.getPresent()); }
		 * catch (ArrayIndexOutOfBoundsException ae){
		 * System.out.println("Paramater ausserhalb des Bereichs vom Vector!");
		 * }
		 * 
		 * }
		 */

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
	public Vector<AppDocument> getDocumentsByUserAndOffer(Account account,
			Offer offer) { // checked; statt Offer offer vielleicht int aid ?
		// Account oder Application Instanz?

		// Vector fuer die Rueckgabe der Unterlagen eines bestimmten Angebots
		// und Accounts bei gegebenem Account und Angebot
		Vector<AppDocument> userOffDocVect = new Vector<AppDocument>();
		ResultSet rs = dbc
				.select(new String[] { "*" },
						new String[] { "Bewerbungsunterlagen" },
						"Benutzername='" + account.getUsername() + "' AND AID="
								+ offer.getAid());

		try {
			while (rs.next()) {
				userOffDocVect.add(new AppDocument(
						rs.getString("Benutzername"), rs.getInt("aid"), rs
								.getInt("uid"), rs.getBoolean("status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}
		/*
		 * //Nur zum Testen, ob alle Bewerbungsunterlagen mit aid und account im
		 * Vector sind for (int i=0;i<userOffDocVect.size();i++){ try{
		 * AppDocument offDoc = userOffDocVect.elementAt(i);
		 * System.out.println("Benutzername="
		 * +offDoc.getUsername()+"  AID="+offDoc
		 * .getoID()+" UID="+offDoc.getdID()+" Status="+offDoc.getPresent()); }
		 * catch (ArrayIndexOutOfBoundsException ae){
		 * System.out.println("Paramater ausserhalb des Bereichs vom Vector!");
		 * }
		 * 
		 * }
		 * 
		 * Kommt in Secure rein zum Testen: Account acci = new
		 * Account("max.payne","adfe3",3,"sfdsf@dqd.com","Max Payne",100,"");
		 * Offer offi = new
		 * Offer(901,"agent47","The Cleaning","Bitte schnell online stellen."
		 * ,false,3,47.3,
		 * "Spuren beseitigen, tatort reinigen, DNA Spurenbeseitigung.",new
		 * Date(3456),new Date(4367),34.5,8,new Date(3452));
		 * 
		 * DocumentController.getInstance().getDocumentsByUserAndOffer(acci,offi)
		 * ;
		 */
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
	public Vector<Document> getAllDocuments() { // checked

		// Vector fuer die Rueckgabe aller vorhandenen Unterlagen
		Vector<Document> allDocVect = new Vector<Document>();
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { "Unterlagen" }, null);

		try {
			while (rs.next()) {
				allDocVect.add(new Document(rs.getInt("uid"), rs
						.getString("name"), rs.getString("beschreibung")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to close the ResultSet.");
			e.printStackTrace();
		}

		/*
		 * //Nur zum Testen, ob alle Unterlagen im Vector sind for (int
		 * i=0;i<allDocVect.size();i++){ try{ Document offDoc =
		 * allDocVect.elementAt(i);
		 * System.out.println("UID="+offDoc.getUid()+"  Name="
		 * +offDoc.getName()+" Beschreibung="+offDoc.getDescription()); } catch
		 * (ArrayIndexOutOfBoundsException ae){
		 * System.out.println("Paramater ausserhalb des Bereichs vom Vector!");
		 * }
		 * 
		 * }
		 */
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
	public void createAppDocument(AppDocument document) { // checked
		dbc.insert("Bewerbungsunterlagen",
				new Object[] { document.getUsername(), document.getoID(),
						document.getdID(), document.getPresent() });
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
	public void deleteAppDocument(AppDocument document) { // checked
		dbc.delete("Bewerbungsunterlagen",
				"benutzername='" + document.getUsername() + "' AND AID="
						+ document.getoID() + " AND UID=" + document.getdID());
	}

	/**
	 * Diese Methode aendert den Status einer Bewerbungsunterlage
	 * beziehungsweise aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Applikationsdokument-Objekt mit allen dazugehoerigen
	 *            Attributen.
	 */
	public void updateAppDocument(AppDocument document) { // checked; Update von
															// Status
															// funktioniert

		String where = "Benutzername='" + document.getUsername() + "' AND AID="
				+ document.getoID() + " AND UID=" + document.getdID();
		String[] columns = new String[] { "Benutzername", "AID", "UID",
				"Status" };
		Object[] values = new Object[] { document.getUsername(),
				document.getoID(), document.getdID(), document.getPresent() };

		/*
		 * Vielleicht noch in der DB pruefen, ob die Bewerbungsunterlage
		 * ueberhaupt vorhanden ist in der Bewerbungsunterlagen-Tabelle. Eine
		 * Fehlermeldung als System.out.println() wuerde reichen. Updatevorgang
		 * eines nichtvorhandenen Eintrags liefert keine Ausgabe, keine
		 * Aenderungen am System. Es sieht aus, als ob das System nichts machen
		 * wuerde.
		 */
		dbc.update("Bewerbungsunterlagen", columns, values, where);
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
	public void createOfferDocument(OfferDocument document) { // checked
		dbc.insert("Standardunterlagen", new Object[] { document.getOfferID(),
				document.getDocumentid() });
	}

	/**
	 * Diese Methode loescht ein Angebotunterlagen-Objekt (OfferDocument-Objekt)
	 * aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein AngebotDokument-Objekt
	 *            mit allen dazugehoerigen Attributen.
	 */
	public void deleteOfferDocument(OfferDocument document) { // checked

		dbc.delete("Standardunterlagen", "AID=" + document.getOfferID()
				+ " AND UID=" + document.getDocumentid());
	}

	/**
	 * Diese Methode aendert die Attribute einer Angebotsunterlage
	 * beziehungsweise aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Angebotsdokument-Objekt mit allen dazugehoerigen Attributen.
	 */
	public void updateOfferDocument(OfferDocument document/* , int newDocumentId */) { // checked:
																						// PROBLEM
		/*
		 * Problem: DocumentController.java Methode updateOfferDocument(). Was
		 * soll bei Standardunterlagen ge�ndert werden? AID soll unver�ndert und
		 * UID up-to-date sein? Aber �bergeben wird ein OfferDocument Objekt,
		 * d.h. der neue Wert des UID muss bei Methodenaufruf mit �bergeben
		 * werden oder? Sonst macht die MEthode ja z.B. beim Aufruf:
		 * 
		 * DocumentController.getInstance().updateOfferDocument(newOfferDocument(
		 * 901,999));
		 * 
		 * In Worten: "Mach ein update bei Standardunterlagen, wo AID=901 und
		 * UID=999 mit new Object [] {document.getOfferID(),
		 * document.getDocumentid()} was beideutet AID=901 bleibt 901 und
		 * UID=999 auch? Macht eine update methode hier �berhaupt Sinn? L�schen
		 * und neu anlegen ist doch praktischer oder? Das w�rde ich vorschlagen.
		 * @author Oemer Sahin
		 */
		String where = "AID=" + document.getOfferID() + " AND UID="
				+ document.getDocumentid();
		String[] columns = new String[] { "AID", "UID" };
		Object[] values = new Object[] { document.getOfferID(),
				document.getDocumentid() };
		// Object[] values = new Object[]{document.getOfferID(),newDocumentId};

		dbc.update("Standardunterlagen", columns, values, where);
	}

	/**
	 * Diese Methode dient dazu, den Status der eingesehenen
	 * Bewerbungsunterlagen anzugeben?
	 */
	public void updateStatus() { // not useful
		/*
		 * Diese Funktionalit�t wird schon in der Methode
		 * updateAppDocument(AppDocument document) realisiert.
		 */
	}
}
