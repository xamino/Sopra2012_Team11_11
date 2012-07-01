package user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import servlet.Helper;

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

//	/**
//	 * Methode zum annehmen eines Bewerbers.
//	 * 
//	 * @param AID
//	 *            ID der Bewerbung
//	 * @throws SQLException
//	 */
//	public void acceptApplication(int AID) throws SQLException {
//		Application app = appcon.getApplicationById(AID);
//		app.setChosen(true);
//		appcon.updateApplication(app);
//	}

//	/**
//	 * Loescht ein Angebot aus dem System.
//	 */
//	public void deleteOffer(Offer offer) {
//		offcon.deleteOffer(offer);
//
//	}

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
}
