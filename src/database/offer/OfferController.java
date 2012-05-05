/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 * @author Manuel Güntzel
 * @author Tamino Hartmann
 */

package database.offer;

/**
 * Verwaltet alle Datenbankzugriffe auf Angebots-bezogene Daten.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import logger.Log;
import user.Provider;
import database.DatabaseController;
import database.account.Account;
import database.application.Application;

public class OfferController {

	/**
	 * Private Instanz des Loggers.
	 */
	private Log log;

	/**
	 * Diese Methode prueft ob ein OfferController-Objekt existiert. Falls nicht
	 * wird eine neue OfferController-Instanz angelegt, zurueckgegeben und in
	 * dem Klassenattribut "offcontr" abgespeichert. Dies dient zur
	 * Gewaehrleistung, dass nur eine Instanz von OfferController existiert.
	 * 
	 * @return Es wird eine Instanz zurueckgegeben.
	 */
	public static OfferController getInstance() {
		if (offcontr == null)
			offcontr = new OfferController();
		return offcontr;
	}

	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private OfferController() {
		dbc = DatabaseController.getInstance();
		log = Log.getInstance();
		log.write("OfferController", "Instance created.");
	}

	/**
	 * Klassenattribut "offcontr" beinhaltet eine OfferController-Instanz, falls
	 * keine vorhanden war und mit der Methode getInstance angelegt wird. Dies
	 * dient zur Gewaehrleistung, dass nur eine Instanz von OfferController
	 * existiert.
	 */
	private static OfferController offcontr;

	/**
	 * Attribut dbc ist eine DatabaseController Instanz und wird fuer den
	 * Datenbankzugang benoetigt.
	 */
	private DatabaseController dbc;

	final static String tableName = "Angebote";// tabellenname

	/**
	 * Diese Methode erstellt ein neues Jobangebot in der Datenbank mit Daten
	 * aus dem uebergebenen Offer-Objekt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen noetigen
	 *            Attributen.
	 */
	public void createOffer(Offer offer) {

		// Object[] values = { offer.getAid(), offer.getAuthor(),
		// offer.getName(),
		// offer.getNote(), offer.isChecked(), offer.getSlots(),
		// offer.getHoursperweek(), offer.getDescription(),
		// offer.getStartdate(), offer.getEnddate(), offer.getWage(),
		// offer.getInstitute(), offer.getModificationdate() };

		Object[] values = { offer.getAid(), offer.getAuthor(), offer.getName(),
				offer.getNote(), offer.isChecked(), offer.getSlots(),
				offer.getHoursperweek(), offer.getDescription(),
				offer.getStartdate(), offer.getEnddate(),
				offer.getWage(), offer.getInstitute(), offer.getModificationdate()};
																	
		dbc.insert(tableName, values);
	}

	/**
	 * Diese Methode loescht ein Jobangebot aus der Datenbank, welches mit den
	 * Daten des uebergebenem Offer-Objekts uebereinstimmt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen noetigen
	 *            Attributen. Uebergebene Instanz wird komplett vom System
	 *            entfernt.
	 */
	public void deleteOffer(Offer offer) {

		dbc.delete("Bewerbungsunterlagen", "AID=" + offer.getAid());
		dbc.delete("Standardunterlagen", "AID=" + offer.getAid());
		dbc.delete("Bewerbungen", "AID=" + offer.getAid());
		dbc.delete(tableName, "AID=" + offer.getAid());

	}

	/**
	 * Diese Methode aendert die Attribute eines Jobangebots beziehungsweise
	 * aktualisiert diese in der Datenbank.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateOffer(Offer offer) {

		// Dates entfernt, da problematisch

		// String[] columns = {"Ersteller", "Name", "Notiz", "Geprueft",
		// "Plaetze", "Stundenprowoche", "Beschreibung", "Beginn", "Ende",
		// "Stundenlohn", "Institut", "aenderungsdatum" };
		//
		// Object[] values = offer.getAuthor(), offer.getName(),
		// offer.getNote(), offer.isChecked(), offer.getSlots(),
		// offer.getHoursperweek(), offer.getDescription(),
		// offer.getStartdate(), offer.getEnddate(), offer.getWage(),
		// offer.getInstitute(), offer.getModificationdate() };
		//
		//
		String[] columns = { "Ersteller", "Name", "Notiz", "Geprueft",
				"Plaetze", "Stundenprowoche", "Beschreibung", "Stundenlohn",
				"Institut" };

		Object[] values = { offer.getAuthor(), offer.getName(),
				offer.getNote(), offer.isChecked(), offer.getSlots(),
				offer.getHoursperweek(), offer.getDescription(),
				offer.getWage(), offer.getInstitute() };

		String where = "AID = " + offer.getAid();

		dbc.update(tableName, columns, values, where);

	}

	/**
	 * Diese Methode sammelt alle Jobangebote aus der Datenbank und speichert
	 * diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen vorhanden Jobangeboten aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getAllOffers() {

		Vector<Offer> offervec = new Vector<Offer>(50, 10);

		String[] select = { "*" };
		String[] from = { tableName };

		ResultSet rs = dbc.select(select, from, null);
		try {
			while (rs.next()) {
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getBoolean(5),
						rs.getInt(6), rs.getDouble(7), rs.getString(8),
						rs.getDate(9), rs.getDate(10), rs.getDouble(11),
						rs.getInt(12), rs.getDate(13));

				offervec.add(currentoff);
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return offervec;
	}

	/**
	 * Diese Methode sammelt alle ueberprueften Jobangebote aus der Datenbank
	 * und speichert diese in einem Vector.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen. (enthaelt boolean "checked"
	 *            Variable, die angibt, ob ein Jobangebot schon ueberprueft
	 *            wurde)
	 * @return Alle Jobangebote in der Datenbank, die dem Wert von "checked"
	 *         entsprechen werden in Form eines Vectors zurueckgegeben.
	 */
	public Vector<Offer> getCheckedOffers() {
		Vector<Offer> offervec = new Vector<Offer>();
		String[] select = { "*" };
		String[] from = { tableName };
		String where = "Geprueft = 1";
		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getBoolean(5),
						rs.getInt(6), rs.getDouble(7), rs.getString(8),
						rs.getDate(9), rs.getDate(10), rs.getDouble(11),
						rs.getInt(12), rs.getDate(13));
				offervec.add(currentoff);
			}
			rs.close();
		} catch (SQLException e) {
			log.write("OfferController", "Error retrieving all checked offers!");
			return null;
		}
		return offervec;
	}

	/**
	 * Diese Methode sammelt alle Jobangebote mit freien Stellen aus der
	 * Datenbank und speichert diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen noch freien Jobangeboten aus der
	 *         Datenbank zurueckgegeben. Frei entspricht freie plaetze (slots)
	 *         >= 1. Wenn keine in der Datenbank sind wird <code>null</code>
	 *         zurueckgegeben.
	 */
	public Vector<Offer> getOffersWithFreeSlots() {
		Vector<Offer> offervec = new Vector<Offer>(50, 10);
		String[] select = { "*" };
		String[] from = { tableName };
		String where = "Plaetze > 0";
		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getBoolean(5),
						rs.getInt(6), rs.getDouble(7), rs.getString(8),
						rs.getDate(9), rs.getDate(10), rs.getDouble(11),
						rs.getInt(12), rs.getDate(13));
				offervec.add(currentoff);
			}
			rs.close();
		} catch (SQLException e) {
			log.write("OfferController", "Error reading free offers!");
			return null;
		}
		return offervec;
	}

	/**
	 * Diese Methode sammelt alle Jobangebote eines Providers aus der Datenbank
	 * und speichert diese in einem Vector.
	 * 
	 * @param provider
	 *            Parameter gibt den ben�tigten Provider an
	 * 
	 * @return Es wird ein Vector mit allen Jobangeboten eines Providers aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getOffersByProvider(Provider provider) {
		Vector<Offer> offervec = new Vector<Offer>(50, 10);
		String[] select = { "*" };
		String[] from = { tableName };
		String where = "Ersteller = '" + provider.getUserData().getUsername()
				+ "'";
		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getBoolean(5),
						rs.getInt(6), rs.getDouble(7), rs.getString(8),
						rs.getDate(9), rs.getDate(10), rs.getDouble(11),
						rs.getInt(12), rs.getDate(13));
				offervec.add(currentoff);
			}
			rs.close();
		} catch (SQLException e) {
			log.write("OfferController", "Error reading free offers!");
			return null;
		}
		return offervec;
	}

	/**
	 * Diese Methode sammelt alle Jobangebote, fuer die Sich ein Bewerber
	 * beworben hat, aus der Datenbank und speichert diese in einem Vector.
	 * 
	 * @param applications
	 *            Parameter "applications" ist ein Vector aus
	 *            Application-Objekten (Bewerbungen) von einem Bewerber
	 * @return Es wird ein Vector mit allen Jobangeboten zurueckgegeben, auf die
	 *         sich ein Bewerber beworben hat.
	 */
	public Vector<Offer> getOffersByApplicatiot(Vector<Application> applications) {

		Vector<Offer> offervec = new Vector<Offer>(50, 10);
		String[] select = { "*" };
		String[] from = { tableName };
		String where;

		for (int i = 0; i < applications.size(); i++) {

			where = "AID = " + applications.elementAt(i).getAid();

			ResultSet rs = dbc.select(select, from, where);
			try {
				while (rs.next()) {
					Offer currentoff;
					currentoff = new Offer(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getBoolean(5),
							rs.getInt(6), rs.getDouble(7), rs.getString(8),
							rs.getDate(9), rs.getDate(10), rs.getDouble(11),
							rs.getInt(12), rs.getDate(13));

					offervec.add(currentoff);
				}

				rs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return offervec;
	}

	/**
	 * Liest ein Angebot anhand der AID aus der Datenbank aus.
	 * 
	 * @param ID
	 *            ID des Angebots
	 * @return Gibt das gesuchte Angebot zurueck
	 */
	public Offer getOfferById(int ID) {
		String[] select = { "*" };
		String[] from = { tableName };
		String where = "AID = " + ID;

		ResultSet rs = dbc.select(select, from, where);

		try {
			if (rs.next()) {
				Offer off = new Offer(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getBoolean(5),
						rs.getInt(6), rs.getDouble(7), rs.getString(8),
						rs.getDate(9), rs.getDate(10), rs.getDouble(11),
						rs.getInt(12), rs.getDate(13));
				return off;
			} else
				return null;
		} catch (SQLException e) {
			logger.Log.getInstance().write("OfferController",
					"Error while reading Offer from Database");
		}
		return null;
	}

	/**
	 * Liest alle zugehoerigen Angebote eines Clerk accounts aus der DB. Ist der
	 * Clerk dem Institut 0 Default zugeordnet, werden ALLE ungeprueften
	 * Angebote unabhaengig des Instituts ausgelesen.
	 * 
	 * @param account
	 *            Der account wessen angebote gelesen werden sollen. Ist
	 *            accounttyp != 3 (Clerk) wird null zurueckgeben!
	 * @return Alle ungeprueften Angebote des entsprechenden Instituts.
	 */
	// DONE: Institut = 0 universal already implemented!
	public Vector<Offer> getUncheckedOffersByClerk(Account account) {
		// If not clerk:
		if (account.getAccounttype() != Account.VERWALTER) {
			log.write("OfferController",
					"Illegal access tried in getUncheckedOffersByClerk()!");
			return null;
		}
		Vector<Offer> offers = new Vector<Offer>();
		// Implement that institut 0 is universal:
		ResultSet rs;
		if (account.getInstitute() == 0) {
			rs = dbc.select(new String[] { "*" }, new String[] { tableName },
					"Geprueft=0");
		} else {
			// Institut in (accountInstitut, 0) secures that Offers of Institut
			// 0 are universally seeable.
			rs = dbc.select(new String[] { "*" }, new String[] { tableName },
					"Geprueft=0 AND Institut IN (" + account.getInstitute()
							+ ",0)");
		}
		try {
			while (rs.next()) {
				offers.add(new Offer(rs.getInt("AID"), rs
						.getString("Ersteller"), rs.getString("Name"), rs
						.getString("Notiz"), rs.getBoolean("Geprueft"), rs
						.getInt("Plaetze"), rs.getDouble("Stundenprowoche"), rs
						.getString("Beschreibung"), rs.getDate("Beginn"), rs
						.getDate("Ende"), rs.getDouble("Stundenlohn"), rs
						.getInt("Institut"), rs.getDate("aenderungsdatum")));
			}
			return offers;
		} catch (SQLException e) {
			log.write("OfferController",
					"Error reading ResultSet in getUncheckedOffersByClerk()!");
			return null;
		}
	}
}