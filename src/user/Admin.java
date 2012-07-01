package user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import logger.Log;
import servlet.Helper;
import userManagement.LoggedInUsers;
import database.DatabaseController;
import database.account.Account;
import database.document.Document;
import database.institute.Institute;

/**
 * Verwaltet alle Aufgaben und Daten eines Admins.
 */
public class Admin extends User {

	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers
	 * klasse aufgenommen.
	 * 
	 * @param username
	 *            Benutzername im System.
	 * @param email
	 *            E-Mail Adresse.
	 * @param name
	 *            Realer Name.
	 * @param session
	 *            Session des Benutzers.
	 */
	public Admin(String username, String email, String name, HttpSession session) {
		super(username, email, name, null, session);
		userManagement.LoggedInUsers.addUser(this);
		this.log = Log.getInstance();
	}

	/**
	 * Löscht einen neuen Account.
	 * 
	 * @param username
	 *            Username des anzulegenden Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */

	public boolean deleteAccount(String username) {
		Account account = acccon.getAccountByUsername(username);
		if (account == null) {
			log.write("Admin", "Can not delete <" + username
					+ ">. Does not exist!");
			// response.setContentType("text/error");
			// response.getWriter().write("This user doesn't seem to exist!");
			return false;
		}
		// If user is currently logged in, we do not allow deletion:
		if (LoggedInUsers.getUserByUsername(username) != null) {
			log.write("Admin", "Can not delete <" + username
					+ "> as currently logged in!");
			return false;
		}
		// check if it's a provider account
		if (account.getAccounttype() == 1) {
			acccon.deleteProviderAccount(account);
		}// check if it's a clerk account
		else if (account.getAccounttype() == 2) {
			acccon.deleteClerkAccount(account);
		}// check if it's an applicant account
		else if (account.getAccounttype() == 3) {
			acccon.deleteApplicantAccount(account);
		}// check if it's an admin account
		else if (account.getAccounttype() == 0) {
			acccon.deleteAccount(account);
		}
		log.write("Admin", "<" + getUserData().getUsername()
				+ "> deleted account with username <" + username + ">");

		return true;
	}

	/**
	 * Methode zum Löschen seines Accounts
	 * 
	 * @return Beim erfolgreichen Entfernen wird ein TRUE zurückgegeben.
	 */
	public boolean deleteOwnAccount() {
		String username = this.getUserData().getUsername();
		Account acc = acccon.getAccountByUsername(username);
		boolean check = acccon.deleteAccount(acc);
		invalidate();

		return check;
	}

	/**
	 * Erstellt einen Account.
	 * 
	 * @param account
	 *            zu loeschender Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean createAccount(Account account) {
		if (!acccon.createAccount(account)) {
			// This can happen if the institute doesn't exist:
			log.write("Admin",
					"Error creating account! Is the institute valid?");
			return false;
		}
		log.write("Admin", "<" + getUserData().getUsername()
				+ "> created account for <" + account.getUsername() + ">.");
		return true;
	}

	/**
	 * Editiert einen Account. Der Benutzername ist dabei nicht aenderbar und
	 * identifiziert den zu aendernden Account in der Datenbank.
	 * 
	 * @param acc
	 *            geaenderter Account.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean editAccount(Account account) {
		// For debugging wrong character set:
		// System.out.println(account.getName());
		if (!acccon.updateAccount(account)) {
			log.write("Admin", "Error modifying account!");
			return false;
		}
		log.write("Admin", "<" + getUserData().getUsername()
				+ "> modified account of <" + account.getUsername() + ">.");
		return true;
	}

	/**
	 * Loescht einen loescht ein Administrator Dokument aus der Datenbank.
	 * 
	 * @param doc
	 *            zu löschendes Dokument.
	 */
	public boolean deleteDoc(Document doc) {
		if (!doccon.deleteDocument(doc)) {
			log.write("Admin", "Error deleting document!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> deleted document <" + doc.getName() + ">.");
			return true;
		}
	}

	/**
	 * Fuegt ein Administrator Dokument hinzu.
	 * 
	 * @param doc
	 *            Das zu erstellende Dokument.
	 * @return Gibt an, ob das Dokument erstellt worden konnte.
	 */
	public boolean addDoc(Document doc) {
		if (!doccon.createDocument(doc)) {
			log.write("Admin", "Error adding a document!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> added document <" + doc.getName() + ">.");
			return true;
		}
	}

	/**
	 * Editiert ein Administrator Dokument.
	 * 
	 * @param doc
	 *            geaendertes Dokument
	 * @return Gibt an, ob das Editieren erfolgreich war.
	 */
	public boolean editDoc(Document doc) {
		if (!doccon.updateDocument(doc)) {
			log.write("Admin", "Error updating document!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> modified document <" + doc.getName() + ">.");
			return true;
		}
	}

	/**
	 * TODO!
	 * 
	 * @param institute
	 * @return
	 */
	public boolean addInstitute(Institute institute) {
		if (!instcon.addInstitute(institute)) {
			log.write("Admin", "Error creating institute!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> added institute <" + institute.getName() + ">.");
			return true;
		}
	}

	/**
	 * TODO!
	 * 
	 * @param institute
	 * @return
	 */
	public boolean deleteInstitute(Institute institute) {
		if (!instcon.deleteInstitute(institute)) {
			log.write("Admin", "Error deleting institute!");
			return false;
		} else {
			log.write("Admin", "<" + getUserData().getUsername()
					+ "> deleted institute <" + institute.getName() + ">.");
			return true;
		}
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
				ret = Helper.jsonAtor(
						new String[] { "hoursMonth", "wage", "startDate",
								"endDate" },
						new Object[] { rs.getInt("StdProMonat"),
								rs.getFloat("Lohn"),
								rs.getString("StartDatum"),
								rs.getString("EndDatum") });
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		if (ret.isEmpty()) {
			log.write("Admin", "Error reading default offer values!");
			return null;
		}
		return ret;
	}

	/**
	 * Speichert die neuen Werte fuer das standard Angebot in der Datenbank.
	 * 
	 * @param hoursMonth
	 *            Die neue Stundenanzahl.
	 * @param wage
	 *            Der standard Lohn.
	 * @param startDate
	 *            Das neue start Datum.
	 * @param endDate
	 *            Das neue end Datum.
	 * @return Flag zur kontrolle ob alles geklappt hat.
	 */
	public boolean writeDefValues(int hoursMonth, float wage, String startDate,
			String endDate) {
		if (!DatabaseController.getInstance()
				.update("Standardangebot",
						new String[] { "StdProMonat", "StartDatum", "EndDatum",
								"Lohn" },
						new Object[] { hoursMonth, startDate, endDate, wage },
						"true")) {
			log.write("Admin", "Error updating default offer!");
			return false;
		} else {
			// log.write("Admin", "<" + getUserData().getUsername()+
			// "> modified default offer.");
			return true;
		}
	}
}