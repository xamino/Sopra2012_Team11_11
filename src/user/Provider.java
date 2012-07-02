package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import servlet.Helper;

import com.google.gson.Gson;

import database.DatabaseController;
import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.offer.Offer;

/**
 * Verwaltet alle Aufgaben und Daten eines Anbieters.
 */
public class Provider extends User {

	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der
	 * LoggedInUsers-Klasse aufgenommen.
	 * 
	 * @param username
	 *            Benutzername im System
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Realer Name
	 * @param session
	 *            Session des Benutzers
	 */
	public Provider(String username, String email, String name,
			HttpSession session) {
		super(username, email, name, null, session);
		userManagement.LoggedInUsers.addUser(this);

	}

	/**
	 * Editiert den eigenen Account. Der Benutzername ist dabei nicht aenderbar
	 * und identifiziert den zu aendernden Account in der Datenbank
	 * 
	 * @param acc
	 *            geaenderter Account
	 */
	public boolean editAccount(Account acc) {
		if (!acccon.updateAccount(acc)) {
			log.write("Appllicant", "Error modifying account!");
			return false;
		}
		log.write("Applicant", "<" + getUserData().getUsername()
				+ "> modified account of <" + acc.getUsername() + ">.");
		return true;
	}

	// /**
	// * Methode zum annehmen eines Bewerbers.
	// *
	// * @param AID
	// * ID der Bewerbung
	// * @throws SQLException
	// */
	// public void acceptApplication(int AID) throws SQLException {
	// Application app = appcon.getApplicationById(AID);
	// app.setChosen(true);
	// appcon.updateApplication(app);
	// }

	// /**
	// * Loescht ein Angebot aus dem System.
	// */
	// public void deleteOffer(Offer offer) {
	// offcon.deleteOffer(offer);
	//
	// }

	// /**
	// * Erstellt ein neues, noch zu pruefendes Angebot im System.
	// */
	// public void createOffer(int pId, String pAuthor, String pName,
	// String pNote, boolean pChecked, int pSlots, double pHours,
	// String pDescription, Date pStartDate, Date pEndDate, double pWage,
	// int pInstitute, Date pModificationdate) {
	//
	// }

	/**
	 * Methode zum Löschen seines Accounts
	 * 
	 * @return Beim erfolgreichen Entfernen wird ein TRUE zurückgegeben. Falls
	 *         irgendwo ein Fehler aufgetretten ist wird ein FALSE
	 *         zurückgegeben.
	 */
	public boolean deleteOwnAccount() {
		invalidate();
		Account thisaccount = AccountController.getInstance()
				.getAccountByUsername(this.getUserData().getUsername());
		return acccon.deleteProviderAccount(thisaccount);
	}

	/**
	 * Liest die Standardwerte eines Angebots aus der Datenbank und gibt sie als
	 * JSON-Objekt zurueck.
	 * 
	 * @return Das JSON-Objekt mit den Werten.
	 */
	public String readDefValues() {
		String ret = new String();
		ResultSet rs = DatabaseController.getInstance().select(
				new String[] { "*" }, new String[] { "Standardangebot" }, null);
		try {
			if (rs.next()) {
				ret = Helper
						.jsonAtor(
								new String[] { "hoursMonth", "startDate",
										"endDate", "wage" },
								new Object[] { rs.getInt("StdProMonat"),
										rs.getString("StartDatum"),
										rs.getString("EndDatum"),
										rs.getFloat("Lohn") });
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		if (ret.isEmpty()) {
			log.write("Provider", "Error reading default offer values!");
			return null;
		}
		return ret;
	}

	/**
	 * Liest den Standardlohn aus der Datenbank aus.
	 * 
	 * @return Standardlohn.
	 */
	public double readDefWage() {
		ResultSet rs = DatabaseController.getInstance().select(
				new String[] { "Lohn" }, new String[] { "Standardangebot" },
				null);
		try {
			if (rs.next()) {
				return rs.getFloat("Lohn");
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			log.write("Provider", "Error reading default wage!");
		}
		return 0;
	}

	/**
	 * Laedt alle Angebote eines Anbieters aus der Datenbank.
	 * 
	 * @return Ein Vektor mit den Angeboten.
	 */
	public Vector<Offer> getOwnOffers() {
		return offcon.getOffersByProvider(getUserData().getUsername());
	}

	/**
	 * Liefert ein JSON-Objekt der wichtigen Accountdaten zurueck.
	 * 
	 * @return Das JSON-Objekt mit den Informationen.
	 */
	public String getJSONAccountInfo() {
		String realName = getUserData().getName();
		String email = getUserData().getEmail();
		String rep = acccon.getAccountByUsername(getUserData().getUsername())
				.getRepresentative();
		return Helper.jsonAtor(new String[] { "realName", "email", "rep" },
				new String[] { realName, email, rep });
	}

	/**
	 * Liest alle Representanten aus der Datenbank.
	 * 
	 * @return Vektor mit Usernames der Representanten.
	 */
	public Vector<String> getRepresentatives() {
		String username = getUserData().getUsername();
		return acccon.getPotentialRepresentatives(username);
	}

	/**
	 * Liest alle Accounts der Bewerber eines Angebots als JSON-Objekt aus.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @return Das JSON-Objekt der Accounts.
	 */
	public String getApplicants(int aid) {
		Vector<Application> apps = appcon.getApplicationsByOffer(aid);
		Vector<Account> acc = new Vector<Account>();
		for (Application app : apps) {
			acc.add(acccon.getAccountByUsername(app.getUsername()));
		}
		return new Gson().toJson(acc, acc.getClass());
	}

	/**
	 * Erstellt ein neues zu pruefende Angebot.
	 * 
	 * @param name
	 *            Der Name des Angebots.
	 * @param notiz
	 *            Die Notiz fuer den Sachbearbeiter.
	 * @param stellen
	 *            Die Anzahl der Stellen.
	 * @param stunden
	 *            Die Stunden pro Monat.
	 * @param beschreibung
	 *            Die Beschreibung des Angebots.
	 * @param startDate
	 *            Das anfangs Datum des Angebots.
	 * @param endDate
	 *            Das Enddatum des Angebots.
	 * @return Ein flag fuer Fehler.
	 */
	public boolean createOffer(String name, String notiz, int stellen,
			double stunden, String beschreibung, Date startDate, Date endDate) {
		// Getting the data from delivered connection content to save it as
		// a new offer-object in the db.
		String ersteller = getUserData().getUsername();
		// wird vom clerk gesetzt, aber default wert wird ausgelesen (von
		// admin gesetzt).
		double lohn = readDefWage();
		boolean checked = false;
		// Generating AID
		int aid = offcon.getNewOffID("Angebote");
		if (aid < 0) {
			log.write(
					"Provider",
					"The received AID from generator was invalid after several attempts. Loop has been interrupted!");
			return false;
		}
		// Set modify date:
		java.util.Date aenderungsdatum_1 = new java.util.Date();
		java.sql.Date aenderungsdatum = new java.sql.Date(
				aenderungsdatum_1.getTime());
		// Load institute:
		int institut = acccon.getAccountByUsername(ersteller).getInstitute();
		// Save new Offer in the DB and response
		Offer offer = new Offer(aid, ersteller, name, notiz, checked, stellen,
				stunden, beschreibung, startDate, endDate, lohn, institut,
				aenderungsdatum, false);
		return offcon.createOffer(offer);
	}

	/**
	 * Loescht ein Angebot aus der Datenbank.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @return Flag fuer Fehler.
	 */
	public boolean deleteOffer(int aid) {
		Offer offtodel = offcon.getOfferById(aid);
		return offcon.deleteOffer(offtodel);
	}

	/**
	 * Laedt ein Angebot aus der Datenbank.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @return Das Angebot.
	 */
	public Offer getOffer(int aid) {
		Offer off = offcon.getOfferById(aid);
		// Sichergehen dass das Angebot von diesem Provider stammt:
		if (off.getAuthor().equals(getUserData().getUsername()))
			return off;
		else
			return null;
	}

	/**
	 * Aktualisiert ein Angebot in der Datenbank.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @param title
	 *            Der Titel des Angebots.
	 * @param description
	 *            Die Beschreibung des Angebots.
	 * @return Flag fuer Fehler.
	 */
	public boolean updateOffer(int aid, String title, String description) {
		Offer offUp = offcon.getOfferById(aid);
		// set modificationdate to current date
		java.util.Date aenderungsdatum_2 = new java.util.Date();
		java.sql.Date aenderungsdatum_toUp = new java.sql.Date(
				aenderungsdatum_2.getTime());
		offUp.setName(title);
		offUp.setDescription(description);
		// sets modificationdate and updates it
		offUp.setModificationdate(aenderungsdatum_toUp);
		return offcon.updateOffer(offUp);
	}

	/**
	 * Akzeptiert die Bewerbung eines Bewerbers auf ein Angebot.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @param username
	 *            Der Benutzername des Bewerbers.
	 * @return Flag fuer Fehler.
	 */
	public boolean selectApplicant(int aid, String username) {
		Application application = appcon.getApplicationByOfferAndUser(aid,
				username);
		if (application.isChosen()) {
			return false;
		}
		application.setChosen(true);
		Offer offertoSetSlots = offcon.getOfferById(aid);
		int freeSlots = offertoSetSlots.getSlots();
		log.write("ProviderServlet",
				" Setting free slots for offer in progress...");
		// No free slots
		if (offcon.getFreeSlotsOfOffer(aid) < 1) {
			return false;
		}
		// reduce freeSlots and update it
		else {
			offertoSetSlots.setSlots(freeSlots - 1);
			offcon.updateOffer(offertoSetSlots);
		}
		log.write("ProviderServlet", "'Bewerber <" + username + "> angenommen!");
		return appcon.updateApplication(application);
	}
}
