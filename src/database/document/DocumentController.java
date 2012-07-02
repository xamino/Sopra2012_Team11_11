/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 * @author Manuel Guentzel
 */

package database.document;

/**
 * Verwaltet alle Datenbankzugriffe auf Unterlagen-bezogene Daten.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import logger.Log;
import database.DatabaseController;
import database.account.Account;
import database.offer.Offer;

public class DocumentController {

	/**
	 * Private Instanz des DatabaseController.
	 */
	private DatabaseController db;

	/**
	 * Name der Tabelle
	 * 
	 */
	final static String tableNameS = "Standardunterlagen";// tabellenname
	/**
	 * Name der Tabelle
	 * 
	 */
	final static String tableNameB = "Bewerbungsunterlagen";// tabellenname
	/**
	 * Name der Tabelle
	 * 
	 */
	final static String tableNameU = "Unterlagen";// tabellenname

	/**
	 * Beinhaltet die ApplicationController-Instanz. Diese wird, falls keine
	 * vorhanden war, mit der Methode <code>getInstance</code> angelegt.
	 */
	private static DocumentController doccontr;
	/**
	 * Private Instanz des loggers.
	 */
	private Log log;

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
		db = DatabaseController.getInstance();
		log = Log.getInstance();
		log.write("DocumentController", "Instance created.");
	}

	/**
	 * Diese Methode erstellt eine Unterlage des Administrators in der
	 * Datenbank. Mit uebergebenem Unterlagen-Objekt (Document-Objekt).
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean createDocument(Document document) { // checked
		return db.insert(
				tableNameU,
				new Object[] { document.getUid(), document.getName(),
						document.getDescription() });
	}

	/**
	 * Diese Methode erstellt eine Unterlage des Administrators in der
	 * Datenbank. Mit uebergebenem Unterlagen-Objekt (Document-Objekt).
	 * 
	 * @param name
	 *            Name des Dokuments
	 * @param beschreibung
	 *            Beschreibung des Dokuments
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean generateDocument(String name, String beschreibung) { // checked

		int uid = 0;
		String[] select = { "MAX(UID)" };
		String[] from = { tableNameU };

		ResultSet rs = db.select(select, from, null);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't generate Document");
			return false;
		}
		try {
			if (rs.next()) {
				uid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		uid++;

		return db.insert(tableNameU, new Object[] { uid, name, beschreibung });
	}

	/**
	 * Diese Methode loescht eine Administrator Unterlage aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean deleteDocument(Document document) { // checked
		/*
		 * Beim Loeschen einer Unterlage muss diese nicht nur in "Unterlagen",
		 * sondern auch in "Standardunterlagen" und "Bewerbungsunterlagen"
		 * geloescht werden, da diese nicht mehr existiert.
		 */
		return db.delete(tableNameB, "UID=" + document.getUid())
				&& db.delete(tableNameS, "UID=" + document.getUid())
				&& db.delete(tableNameU, "UID=" + document.getUid());
	}

	/**
	 * Diese Methode aendert die Attribute einer Administrator Unterlage bzw.
	 * aktualisiert diese in der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein Document-Objekt mit
	 *            allen dazugehoerigen Attributen.
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean updateDocument(Document document) { // checked
		return db.update(tableNameU, new String[] { "Name", "Beschreibung" },
				new Object[] { document.getName(), document.getDescription() },
				"UID=" + document.getUid());
	}

	public Document getDocumentByUID(int uid) {
		Document doc = null;
		ResultSet rs = db.select(new String[] { "*" },
				new String[] { tableNameU }, "UID=" + uid);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get document");
			return null;
		}
		try {
			if (rs.next()) {
				doc = new Document(rs.getInt("UID"), rs.getString("Name"),
						rs.getString("Beschreibung"));
			}
		} catch (SQLException e) {
			log.write("DocumentController",
					"Error in getting document with UID=" + uid);
			// e.printStackTrace();
		}
		return doc;
	}

	/**
	 * Diese Methode sammelt alle Unterlagen zu einem bestimmten Jobangebot aus
	 * der Datenbank und speichert diese in einem Vector.
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
		ResultSet rs = db.select(new String[] { "*" },
				new String[] { tableNameS }, "AID=" + aid);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return docVect;
		}
		try {
			while (rs.next()) {
				docVect.add(new OfferDocument(rs.getInt("AID"), rs
						.getInt("UID")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
		return docVect;
	}

	/**
	 * Die Methode liefert zu einer Bewerbung alle abgegebenen Dokumente.
	 * 
	 * @param benutzername
	 *            Username vom Bewerber.
	 * @return Gibt alle Bewerbungsunterlagen mit abgeschlossenem Status (=1)
	 *         zurueck.
	 */
	public Vector<AppDocument> getAppDocsWithStatusOne(String benutzername,
			int aid) {
		String[] select = { "*" };
		String[] from = { tableNameB };
		String where = "benutzername='" + benutzername + "' AND AID = " + aid
				+ " AND status = 'TRUE'";
		Vector<AppDocument> appdoc = new Vector<AppDocument>();
		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return appdoc;
		}
		try {
			while (rs.next()) {
				AppDocument temp;
				temp = new AppDocument(rs.getString(0), rs.getInt(1),
						rs.getInt(2), rs.getBoolean(3));
				appdoc.add(temp);
			}
		} catch (SQLException e) {
			log.write("DocumentController", "Error while select");
			// e.printStackTrace();
			return null;
		}

		return appdoc;

	}

	/**
	 * Die Methode liefert zu einer Bewerbung alle nicht abgegebenen Dokumente.
	 * 
	 * @param benutzername
	 *            Username vom Bewerber.
	 * @return Gibt alle Bewerbungsunterlagen mit nicht abgeschlossenem Status (=0)
	 *         zurueck.
	 */
	public Vector<AppDocument> getAppDocsWithStatusZero(String benutzername,
			int aid) {
		String[] select = { "*" };
		String[] from = { tableNameB };
		String where = "benutzername='" + benutzername + "' AND AID = " + aid
				+ " AND status = 'FALSE'";
		Vector<AppDocument> appdoc = new Vector<AppDocument>();
		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return appdoc;
		}
		try {
			while (rs.next()) {
				AppDocument temp;
				temp = new AppDocument(rs.getString(0), rs.getInt(1),
						rs.getInt(2), rs.getBoolean(3));
				appdoc.add(temp);
			}
		} catch (SQLException e) {
			log.write("DocumentController", "Error while select");
			// e.printStackTrace();
			return null;
		}

		return appdoc;

	}

	/**
	 * Diese Methode sammelt alle Bewerber-Unterlagen zu einem bestimmten
	 * Jobangebot und Bewerber aus der Datenbank und speichert diese in einem
	 * Vector.
	 * 
	 * @param aid
	 *            Parameter "aid" (Angebots-Id) ist die Id des Jobangebots.
	 * @param username
	 *            Der Benutzername des Bewerers.
	 * @return Es wird ein Vector mit allen Unterlagen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<AppDocument> getAppDocument(int aid, String username) {

		// Vector fuer die Rueckgabe der Bewerbungsunterlagen eines bestimmten
		// Angebots bei gegebener Angebots-ID
		Vector<AppDocument> appDocVect = new Vector<AppDocument>();
		ResultSet rs = db.select(new String[] { "*" },
				new String[] { tableNameB }, "AID=" + aid
						+ " AND benutzername='" + username + "'");
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return appDocVect;
		}
		try {
			while (rs.next()) {
				appDocVect.add(new AppDocument(rs.getString("benutzername"), rs
						.getInt("AID"), rs.getInt("UID"), rs
						.getBoolean("status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
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
		ResultSet rs = db.select(
				new String[] { "*" },
				new String[] { tableNameB },
				"benutzername='" + account.getUsername() + "' AND AID="
						+ offer.getAid());
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return userOffDocVect;
		}
		try {
			while (rs.next()) {
				userOffDocVect.add(new AppDocument(
						rs.getString("benutzername"), rs.getInt("AID"), rs
								.getInt("UID"), rs.getBoolean("status")));
			}
		} catch (SQLException e) {
			log.write("DocumentController",
					"There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
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
		ResultSet rs = db.select(new String[] { "*" },
				new String[] { tableNameU }, null);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return allDocVect;
		}

		try {
			while (rs.next()) {
				allDocVect.add(new Document(rs.getInt("UID"), rs
						.getString("Name"), rs.getString("Beschreibung")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("There was an error while trying to cast from ResultSet to Document-Object.");
			e.printStackTrace();
		}
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
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean createAppDocument(AppDocument document) { // checked
		return db.insert(tableNameB, new Object[] { document.getUsername(),
				document.getoID(), document.getdID(), document.getPresent() });
	}

	/**
	 * Diese Methode loescht ein Applikationsunterlagen-Objekt
	 * (AppDocument-Objekt) aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Applikationsdokument-Objekt mit allen dazugehoerigen
	 *            Attributen.
	 * @return  Gibt an, ob die Operation erfolgreich war.           
	 */
	public boolean deleteAppDocument(AppDocument document) { // checked
		return db.delete(tableNameB,
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
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean updateAppDocument(AppDocument document) { // checked; Update
																// von
																// Status
																// funktioniert

		String where = "benutzername='" + document.getUsername() + "' AND AID="
				+ document.getoID() + " AND UID=" + document.getdID();
		String[] columns = new String[] { "benutzername", "AID", "UID",
				"status" };
		Object[] values = new Object[] { document.getUsername(),
				document.getoID(), document.getdID(), document.getPresent() };

		// /*
		// * Vielleicht noch in der DB pruefen, ob die Bewerbungsunterlage
		// * ueberhaupt vorhanden ist in der Bewerbungsunterlagen-Tabelle. Eine
		// * Fehlermeldung als System.out.println() wuerde reichen.
		// Updatevorgang
		// * eines nichtvorhandenen Eintrags liefert keine Ausgabe, keine
		// * Aenderungen am System. Es sieht aus, als ob das System nichts
		// machen
		// * wuerde.
		// */
		return db.update(tableNameB, columns, values, where);
	}

	/**
	 * Diese Methode liefert alle Dokumente zu einer uebergebenen AID, 
	 * einem uebergebenem username und einer uebergebenen UID zurueck.
	 * 
	 * @param username
	 * 			Benutzername
	 * @param aid
	 * 			AngebotsID
	 * @param uid
	 * 			UnterlagenID
	 * @return liefert die geuchten Dokumente zurueck
	 */
	public AppDocument getDocumentByUsernameAIDandUID(String username, int aid,
			int uid) {
		String[] select = { "*" };
		String[] from = { tableNameB };
		String where = "benutzername = '" + username + "' AND AID =" + aid
				+ " AND UID =" + uid;

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get document");
			return null;
		}
		try {
			if (rs.next()) {
				AppDocument doc = new AppDocument(rs.getString("benutzername"),
						rs.getInt("AID"), rs.getInt("UID"),
						rs.getBoolean("status"));
				return doc;
			} else
				return null;
		} catch (SQLException e) {
			logger.Log.getInstance().write("DocumentController",
					"Error while reading AppDocument from Database");
		}
		return null;
	}

	/**
	 * Diese Methode erstellt ein Angebotsunterlagen-Objekt
	 * (OfferDocument-Objekt) in der Datenbank. Mit ubergebenem
	 * Angebotsunterlagen-Objekt (OfferDocument-Objekt).
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein
	 *            Angebotsdokument-Objekt mit allen dazugehoerigen Attributen.
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean createOfferDocument(OfferDocument document) { // checked
		return db.insert(tableNameS, new Object[] { document.getOfferID(),
				document.getDocumentid() });
	}

	/**
	 * Diese Methode loescht ein Angebotunterlagen-Objekt (OfferDocument-Objekt)
	 * aus der Datenbank.
	 * 
	 * @param document
	 *            Parameter <code>document</code> ist ein AngebotDokument-Objekt
	 *            mit allen dazugehoerigen Attributen.
	 * @return  Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean deleteOfferDocument(OfferDocument document) { // checked
		return db.delete(tableNameS, "AID=" + document.getOfferID()
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
		 * 
		 * @author Oemer Sahin
		 */
		String where = "AID=" + document.getOfferID() + " AND UID="
				+ document.getDocumentid();
		String[] columns = new String[] { "AID", "UID" };
		Object[] values = new Object[] { document.getOfferID(),
				document.getDocumentid() };
		// Object[] values = new Object[]{document.getOfferID(),newDocumentId};

		db.update(tableNameS, columns, values, where);
	}

//	/**
//	 * Diese Methode dient dazu, den Status der eingesehenen
//	 * Bewerbungsunterlagen anzugeben
//	 */
//	public void updateStatus() { // not useful
//		/*
//		 * Diese Funktionalit�t wird schon in der Methode
//		 * updateAppDocument(AppDocument document) realisiert.
//		 */
//	}

	/**
	 * Diese Methode gibt alle Dokumente zurueck, die ein Angebot nicht als
	 * Standartunterlagen angegeben hat.
	 * 
	 * @param aid
	 *            AngebotsID des Angebots
	 * @return Dokumente, die ein Angebot nicht als Standartunterlagen angegeben
	 *         haben.
	 */
	public Vector<Document> getDocumentsToAddToOffer(int aid) {

		Vector<Document> docsToAdd = new Vector<Document>();

		String[] select = { "*" };
		String[] from = { tableNameU };
		String where = "UID not in (Select UID FROM " + tableNameS
				+ " WHERE AID = " + aid + ")";

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return docsToAdd;
		}
		try {
			while (rs.next()) {
				docsToAdd.add(new Document(rs.getInt(1), rs.getString(2), rs
						.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docsToAdd;
	}

	/**
	 * Diese Methode gibt alle Dokumente zurueck, die eine Bewerbung nicht als
	 * Unterlagen angegeben hat.
	 * 
	 * @param aid
	 *            AngebotsID des Angebots
	 * @param username
	 *            Benutzername des Bewerbers
	 * @return Dokumente, die eine Bewerbung nicht als Unterlagen angegeben haben.
	 */
	public Vector<Document> getDocumentsToAddToApp(int aid, String username) {
		Vector<Document> docsToAdd = new Vector<Document>();

		String[] select = { "*" };
		String[] from = { tableNameU };
		String where = "UID not in (Select UID FROM " + tableNameB
				+ " WHERE AID = " + aid + " AND benutzername ='" + username
				+ "')";

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return docsToAdd;
		}
		try {
			while (rs.next()) {
				docsToAdd.add(new Document(rs.getInt(1), rs.getString(2), rs
						.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docsToAdd;
	}

	/**
	 * Die Methode liefert zu einem Username alle vorhandenen Dokumente.
	 * 
	 * @param benutzername
	 *            Username vom Bewerber.
	 * @return Vektor mit allen Dokumenten zu einem Username.
	 */
	public Vector<AppDocument> getAllAppDocsByApplicant(String benutzername) {
		String[] select = { "*" };
		String[] from = { tableNameB };
		String where = "benutzername='" + benutzername + "'";
		Vector<AppDocument> appdoc = new Vector<AppDocument>();
		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get documents");
			return appdoc;
		}
		try {
			while (rs.next()) {
				AppDocument temp;
				temp = new AppDocument(rs.getString(0), rs.getInt(1),
						rs.getInt(2), rs.getBoolean(3));
				appdoc.add(temp);
			}
		} catch (SQLException e) {
			log.write("DocumentController", "Error while select");
			// e.printStackTrace();
			return null;
		}

		return appdoc;

	}

	/**
	 * Holt eine einzelne Bewerbungsunterlage anhand der UID aus der Datenbank.
	 * 
	 * @param UID
	 *            Die UID.
	 * @return Die Bewerbungsunterlage. <code>Null</code> wenn Fehler.
	 */
	public AppDocument getAppDocumentByUID(final int UID) {
		String[] select = { "*" };
		String[] from = { tableNameB };
		String where = "UID=" + UID;
		AppDocument appdoc;
		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("DocumentController",
					"No connection: couldn't get document");
			return null;
		}
		try {
			// TODO: Fehler -> hier werden mehr als nur 1 Dokument
			// zurückgeliefert!
			if (rs.next()) {
				appdoc = new AppDocument(rs.getString("benutzername"),
						rs.getInt("AID"), rs.getInt("UID"),
						rs.getBoolean("status"));
				return appdoc;
			}
			return null;
		} catch (SQLException e) {
			log.write("DocumentController", "Error in getAppDocumentByUID()!");
			return null;
		}
	}

	/**
	 * Generiert eine neue ID.
	 * 
	 * @return generierte ID
	 */
	public int getNewDocID(String tablename) {
		int newID = 0;
		boolean check = false;
		while (!check) {
			newID = generateRandomNr(1, 9999);
			Object[] data = { newID, "TempName", "TempText" };
			check = db.insert(tablename, data);
		}
		db.delete(tablename, "UID= " + newID);
		return newID;
	}

	/**
	 * Generiert eine neue Zufallszahl.
	 * 
	 * @param aStart
	 *            Start der Zahl.
	 * @param aEnd
	 *            Ende der Zahl.
	 * @return generierte Zahl.
	 */
	private int generateRandomNr(int aStart, int aEnd) {

		Random random = new Random();
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}
}
