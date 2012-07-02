/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 * @author Manuel Güntzel
 * @author Anatoli Brill
 */

package database.application;

/**
 * Verwaltet alle Datenbankzugriffe auf Bewerbungs-bezogene Daten.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import logger.Log;

import database.DatabaseController;
import database.HilfsDatenClerk;
import database.account.Account;
import database.account.AccountController;
import database.document.AppDocument;
import database.document.DocumentController;
import database.document.OfferDocument;
import database.offer.Offer;
import database.offer.OfferController;

public class ApplicationController {

	/**
	 * Klassenattribut "appcon" beinhaltet eine ApplicationController-Instanz,
	 * falls keine vorhanden war und mit der Methode getInstance angelegt wird.
	 * Dies dient zur Gewaehrleistung, dass nur eine Instanz von
	 * ApplicationController existiert. Das selbe gilt auch fuer die anderen
	 * Controller.
	 */
	private static ApplicationController appcon;
	private static AccountController acccon;
	private static DocumentController doccon;
	private static DatabaseController db;
	/**
	 * Instanz des Loggers.
	 */
	private Log log;

	/**
	 * Diese Methode prueft ob ein ApplicationController-Objekt existiert. Falls
	 * nicht wird eine neue ApplicationOffer-Instanz angelegt, zurueckgegeben
	 * und in dem Klassenattribut "appcontr" abgespeichert. Dies dient zur
	 * Gewaehrleistung, dass nur eine Instanz von ApplicationController
	 * existiert.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static ApplicationController getInstance() {
		if (appcon == null)
			appcon = new ApplicationController();
		return appcon;

	}

	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private ApplicationController() {
		db = DatabaseController.getInstance();
		acccon = AccountController.getInstance();
		doccon = DocumentController.getInstance();
		log = Log.getInstance();
		log.write("ApplicationController", "Instance created.");
	}

	final static String tableName = "Bewerbungen";// tabellenname

	/**
	 * Diese Methode erstellt eine Bewerbung in der Datenbank. Mit ubergebenem
	 * Bewerbungsobjekt.
	 * 
	 * @param application
	 *            Parameter "application" ist ein Application-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public boolean createApplication(Application application) { // checked
		Vector<Boolean> check = new Vector<Boolean>();
		Object[] values = { application.getUsername(), application.getAid(),
				application.isFinished(), application.getClerk(),
				application.isChosen() };
		check.add(db.insert(tableName, values));
		Vector<OfferDocument> offs = doccon.getDocumentsByOffer(application
				.getAid());
		for (OfferDocument o : offs) {
			check.add(db.insert(
					"Bewerbungsunterlagen",
					new Object[] { application.getUsername(),
							application.getAid(), o.getDocumentid(), false }));
		}
		for (Boolean b : check)
			if (!b)
				return false;
		return true;
	}
	


	/**
	 * Diese Methode loescht eine Bewerbung aus der Datenbank. Mit ubergebenem
	 * Bewerbungsobjekt.
	 * 
	 * @param application
	 *            Parameter "application" ist ein Application-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */

	public boolean deleteApplication(Application application) {
		String where = "AID = " + application.getAid()
				+ " AND benutzername = '" + application.getUsername() + "'";

		Account useracc = acccon
				.getAccountByUsername(application.getUsername());
		Offer offer = OfferController.getInstance().getOfferById(
				application.getAid());

		// deletes all appdocuments from this application
		Vector<AppDocument> docs = doccon.getDocumentsByUserAndOffer(useracc,
				offer);
		if (docs != null) {
			for (int i = 0; i < docs.size(); i++) {
				doccon.deleteAppDocument(docs.elementAt(i));
			}
		}

		return db.delete(tableName, where);
	}

	/**
	 * Diese Methode aendert die Attribute einer Bewerbung bzw. aktualisiert
	 * diese in der Datenbank.
	 * 
	 * @param application
	 *            Parameter "application" ist ein Application-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateApplication(Application application) {

		String[] columns = { /* "benutzername", */"status", "sachbearbeiter",
				"ausgewaehlt" };
		Object[] values = { /* application.getUsername(), */
		application.isFinished(), application.getClerk(),
				application.isChosen() };
		String where = "AID = " + application.getAid() + " AND benutzername='"
				+ application.getUsername() + "'";

		db.update(tableName, columns, values, where);
	}

	/**
	 * Diese Methode sammelt alle Bewerbungen aus der Datenbank und speichert
	 * diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen vorhanden Bewerbungen in der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Application> getAllApplications() {

		Vector<Application> applicationvec = new Vector<Application>(50, 10);

		String[] select = { "*" };
		String[] from = { tableName };

		ResultSet rs = db.select(select, from, null);
		if (rs == null) {
			log.write("ApplicationController",
					"No connection: couldnt get applications");
			return applicationvec;
		}
		try {
			while (rs.next()) {
				Application currentapp;
				currentapp = new Application(rs.getString(1), rs.getInt(2),
						rs.getBoolean(3), rs.getString(4), rs.getBoolean(5));

				applicationvec.add(currentapp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applicationvec;
	}


	/**
	 * Diese Methode sammelt alle Bewerbungen eines bestimmten Bewerbers aus der
	 * Datenbank und speichert diese in einem Vector.
	 * 
	 * @param username
	 *            Parameter "username" gibt den Namen dese Bewerbers an, dessen
	 *            Bewerbungen angezeigt werden sollen.
	 * @return Alle Bewerbungen zu einem Bewerber aus der Datenbank in Form
	 *         eines Vectors werden zurueckgegeben.
	 */
	public Vector<Application> getApplicationsByApplicant(String username) {

		Vector<Application> applicationvec = new Vector<Application>(3, 2);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "benutzername = '" + username + "'";

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("ApplicationController",
					"No connection: couldnt get applications");
			return applicationvec;
		}
		try {
			while (rs.next()) {
				Application currentapp;
				currentapp = new Application(rs.getString(1), rs.getInt(2),
						rs.getBoolean(3), rs.getString(4), rs.getBoolean(5));

				applicationvec.add(currentapp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return applicationvec;

	}

	/**
	 * Diese Methode sammelt alle Bewerbungen zu einem bestimmten Jobangebot aus
	 * der Datenbank und speichert diese in einem Vector.
	 * 
	 * @param aid
	 *            Parameter "aid" (Angebots-Id) ist die Id des Jobangebots.
	 * @return Es wird ein Vector mit allen Bewerbungen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<Application> getApplicationsByOffer(int aid) {

		Vector<Application> applicationvec = new Vector<Application>(10, 5);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "AID = " + aid;

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("ApplicationController",
					"No connection: couldnt get applications");
			return applicationvec;
		}
		try {
			while (rs.next()) {
				Application currentapp;
				currentapp = new Application(rs.getString(1), rs.getInt(2),
						rs.getBoolean(3), rs.getString(4), rs.getBoolean(5));

				applicationvec.add(currentapp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return applicationvec;

	}

	/**
	 * Gibt alle Bewerbungen die von einem Verwalter abgeschlossen wurden
	 * zurueck.
	 * 
	 * @param clerkname
	 *            Name des Verwalters.
	 * @return Es wird ein Vektor mit Bewerbungen zurueckgegeben.
	 */
	public Vector<Application> getApprovedApplicationsByClerk(String clerkname) {

		Vector<Application> applicationvec = new Vector<Application>(50, 10);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "sachbearbeiter = '" + clerkname + "'";

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("ApplicationController",
					"No connection: couldnt get applications");
			return applicationvec;
		}
		try {
			while (rs.next()) {
				Application currentapp;
				currentapp = new Application(rs.getString(1), rs.getInt(2),
						rs.getBoolean(3), rs.getString(4), rs.getBoolean(5));

				applicationvec.add(currentapp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return applicationvec;
	}

	/**
	 * Gibt eine Bestimmte Bewerbung zurueck. Die Bewerbung wird eindeutig durch
	 * die ID bestimmt.
	 * 
	 * @param AId
	 *            Id einer Bewerbung
	 * @return Es wird die gesuchte Bewerbung zurueck gegeben.
	 */
	public Application getApplicationById(int AId) {
		String[] select = { "*" };
		String[] from = { tableName };
		String where = "AID = " + AId;

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("ApplicationController",
					"No connection: couldnt get application");
			return null;
		}

		try {
			if (rs.next()) {
				Application app = new Application(rs.getString(1),
						rs.getInt(2), rs.getBoolean(3), rs.getString(4),
						rs.getBoolean(5));
				return app;
			} else
				return null;
		} catch (SQLException e) {
			logger.Log.getInstance().write("ApplicantionController",
					"Error while reading application by aid from Database");
		}
		return null;

		// changed by oemer. orginal:
		// public Application getApplicationById(int AId) throws SQLException {
		// String[] select = { "AID" };
		// String[] from = { tableName };
		// String where = null;
		//
		// ResultSet rs = db.select(select, from, where);
		// Application app = new Application(rs.getString(1), rs.getInt(2),
		// rs.getBoolean(3), rs.getString(4), rs.getBoolean(5));
		// return app;
		// }

	}

	/**
	 * Gibt eine Bestimmte Bewerbung zurueck. Die Bewerbung wird eindeutig durch
	 * die AngebotsID und dem Benutzernamen bestimmt.
	 * 
	 * @param AnId
	 *            Id eines Angebots
	 * @param username
	 *            Benutzername eines Bewerbers
	 * @return Es wird die gesuchte Bewerbung zurueck gegeben.
	 */
	public Application getApplicationByOfferAndUser(int AnId, String username) {
		String[] select = { "*" };
		String[] from = { tableName };
		String where = "AID = " + AnId + " AND benutzername ='" + username
				+ "'";

		ResultSet rs = db.select(select, from, where);
		if (rs == null) {
			log.write("ApplicationController",
					"No connection: couldnt get application");
			return null;
		}

		try {
			if (rs.next()) {
				Application app = new Application(rs.getString(1),
						rs.getInt(2), rs.getBoolean(3), rs.getString(4),
						rs.getBoolean(5));
				return app;
			} else
				return null;
		} catch (SQLException e) {
			logger.Log.getInstance().write("ApplicantionController",
					"Error while reading application by aid from Database");
		}
		return null;
	}

	/**
	 * Gibt Bewerbername und Angebotsname aller angenommenen, noch nicht beendeten Bewerbungen des
	 * uebergebenen Instituts in Form eines Vectors des Datentyps
	 * HilfsDatenClerk zurueck.
	 * 
	 * @param institute
	 *            Filtert den zurueckgegebenen Datensatz (nur uebergebenes
	 *            Institut)
	 * @return Vector mit Bewerbername und Angebotsname aller angenommenen
	 *         Bewerbungen des uebergebenen Instituts
	 */
	public Vector<HilfsDatenClerk> getChosenAndNotFinishedApplicationDataByInstitute(
			int institute) {
		ResultSet rs;
		try {
			rs = db.select(
					new String[] { "Accounts.name", "Angebote.Name",
							"Accounts.benutzername", "Angebote.AID" },
					new String[] { "Bewerbungen JOIN Angebote ON Bewerbungen.AID = Angebote.AID"
							+ " AND ausgewaehlt = 1 AND status = 0 AND Angebote.Institut = "
							+ institute
							+ " JOIN Accounts ON Accounts.benutzername = Bewerbungen.benutzername" },
					null);
			Vector<HilfsDatenClerk> hdc = new Vector<HilfsDatenClerk>();
			while (rs.next()) {
				// System.out.println(rs.getString(1));
				hdc.add(new HilfsDatenClerk(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4)));
			}
			return hdc;
		} catch (SQLException e) {
			log.write("ApplicationController",
					"Fehler bei getChosenApplicationDataByInstitute()!");
		}
		return null;
	}
	
}
