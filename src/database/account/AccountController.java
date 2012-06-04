/**
 * @author Patryk Boczon 
 * @author Oemer Sahin
 * @author Manuel Güntzel
 **/
package database.account;

/**
 * Verwaltet alle Datenbankzugriffe auf Account-bezogene Daten.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import servlet.Helper;
import user.Applicant;
import user.Clerk;
import user.Provider;

import database.DatabaseController;
import database.application.Application;
import database.application.ApplicationController;
import database.document.AppDocument;
import database.document.DocumentController;
import database.institute.Institute;
import database.institute.InstituteController;
import database.offer.Offer;
import database.offer.OfferController;

/**
 * Der Accountcontroller behandelt die Verwaltungsmethoden von allen Accounts.
 * 
 */

public class AccountController {

	/**
	 * Klassenattribut "acccontr" beinhaltet eine AccountController-Instanz,
	 * falls keine vorhanden war und mit der Methode getInstance angelegt wird.
	 * Dies dient zur Gewaehrleistung, dass nur eine Instanz von
	 * AccountController. existiert.
	 */
	private static AccountController acccontr;

	final static String tableName = "Accounts";// tabellenname

	/**
	 * Diese Methode prueft ob ein AccountController-Objekt existiert. Falls
	 * nicht wird eine neue AccountController-Instanz angelegt, zurueckgegeben
	 * und in dem Klassenattribut "acccontr" abgespeichert. Dies dient zur
	 * Gewaehrleistung, dass nur eine Instanz von AccountController existiert.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static AccountController getInstance() {
		if (acccontr == null)
			acccontr = new AccountController();
		return acccontr;

	}

	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private AccountController() {
		dbc = DatabaseController.getInstance();
		logger.Log.getInstance()
				.write("AccountController", "Instance created.");
	}

	/**
	 * Diese Instanz dient zum Zugang in die Datenbank.
	 */
	public DatabaseController dbc;

	/**
	 * Diese Methode erstellt einen neuen Account mit uebergebener Account
	 * Instanz als Parameter.
	 * 
	 * @param account
	 *            Parameter "account" ist ein Account-Objekt, welches alle
	 *            noetigen Attribute enthaelt.
	 * @return Gibt an, ob das erstellen des Accounts erfolgreich war.
	 */
	public boolean createAccount(Account account) { // checked

		Object[] values = { account.getUsername(), account.getPasswordhash(),
				account.getAccounttype(), account.getEmail(),
				account.getName(), account.getInstitute(),
				account.getRepresentative() };

		return dbc.insert(tableName, values);
	}

	/**
	 * Methode zum löschen eines ApplicantAccounts.
	 * 
	 * @param applicant
	 *            Applicant-Objekt
	 * @return TRUE falls das Löschen erfolgreich war. Ansonsten FALSE
	 */
	public boolean deleteApplicantAccount(Applicant applicant) {
		String username = applicant.getUserData().getUsername();
		Account acc = getAccountByUsername(username);

		Vector<AppDocument> doc = DocumentController.getInstance()
				.getAllAppDocsByApplicant(username);
		for (AppDocument tempDoc : doc) {
			DocumentController.getInstance().deleteAppDocument(tempDoc);
		}

		Vector<Application> apps = ApplicationController.getInstance()
				.getApplicationsByApplicant(username);
		Iterator<Application> itp = apps.iterator();

		for (int i = 0; itp.hasNext(); i++) {
			ApplicationController.getInstance().deleteApplication(
					apps.elementAt(i));
		}

		return deleteAccount(acc);
	}

	/**
	 * Methode zum löschen eines ClerkAccounts
	 * 
	 * @param clerk
	 *            Clerk-Objekt
	 * @return TRUE falls das Löschen erfolgreich war. Ansonsten FALSE
	 */

	public boolean deleteClerkAccount(Clerk clerk) {
		String username = clerk.getUserData().getUsername();
		Account acc = getAccountByUsername(username);

		Institute inst = InstituteController.getInstance().getInstituteByIID(
				acc.getInstitute());
		InstituteController.getInstance().deleteInstitute(inst);
		return deleteAccount(acc);

	}

	/**
	 * Methode zum löschen eines ProviderAccounts
	 * 
	 * @param provider
	 *            Provider-Objekt
	 * @return TRUE falls das Löschen erfolgreich war. Ansonsten FALSE
	 */
	public boolean deleteProviderAccount(Provider provider) {
		String username = provider.getUserData().getUsername();
		Account acc = getAccountByUsername(username);

		Vector<Offer> off = OfferController.getInstance().getOffersByProvider(
				provider);
		Iterator<Offer> it = off.iterator();

		for (int i = 0; it.hasNext(); i++) {
			Offer temp = off.elementAt(i);

			OfferController.getInstance().deleteOffer(temp);

			/* wird doch schon alles bei deleteOffer geloescht
			Vector<AppDocument> doc = DocumentController.getInstance()
					.getAppDocumentByOffer(temp.getAid());
			Iterator<AppDocument> itp = doc.iterator();

			for (int j = 0; itp.hasNext(); j++) {
				DocumentController.getInstance().deleteAppDocument(
						doc.elementAt(j));
			}
			*/
			
		}
		return deleteAccount(acc);
	}

	/**
	 * Diese Methode loescht den gewuenschten Account mit uebergebenem
	 * Account-Objekt.
	 * 
	 * @param account
	 *            Parameter "account" ist ein Account-Objekt, welches alle
	 *            noetigen Attribute enthaelt.
	 * @return
	 */
	public boolean deleteAccount(Account account) { // checked
		String where = "benutzername = '" + account.getUsername() + "'";
		return dbc.delete("Accounts", where);
	}

	/**
	 * Diese Methode nimmt Aenderungen an einem Account-Objekt vor. Dabei werden
	 * die geaenderten Attribute vom uebergebenem Parameter uebernommen.
	 * 
	 * @param account
	 *            Parameter "account" ist ein Account-Objekt, welches alle
	 *            noetigen Attribute enthaelt.
	 * @return Gibt an, ob die Operation erfolgreich war.
	 */
	public boolean updateAccount(Account account) { // checked

		String where = "benutzername = '" + account.getUsername() + "'";

		Object[] values = { account.getPasswordhash(),
				account.getAccounttype(), account.getEmail(),
				account.getName(), account.getInstitute(),
				account.getRepresentative() };

		String[] columns = { "passworthash", "accounttyp", "email", "name",
				"institut", "stellvertreter" };

		return dbc.update(tableName, columns, values, where);
	}

	/**
	 * Gibt die Anzahl der registrierten Accounts zurück.
	 * 
	 * @return Anzahl der Accounts
	 */
	public int accountCount() {
		return dbc.count(new String[] { tableName }, null);

	}

	/**
	 * Diese Methode gibt alle in der Datenbank gespeicherten Accounts zurück.
	 * 
	 * @return Account-Objekt das diesem Username entspricht. Falls nicht
	 *         existent <code>null</code>.
	 */
	public Vector<Account> getAllAccounts() { // checked
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { tableName }, null);
		Vector<Account> accounts = new Vector<Account>();
		try {
			while (rs.next()) {
				accounts.add(new Account(rs.getString(1), rs.getString(2), rs
						.getInt(3), rs.getString(4), rs.getString(5), rs
						.getInt(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			logger.Log.getInstance().write("AccountController",
					"Error while reading all accounts from Database.");
		}
		return accounts;
	}

	/**
	 * Diese Methode gibt den Account mit dem spezifizierten Username zurueck.
	 * Sollte dieser nicht existieren bekommt man einen <code>null</code>
	 * -Verweis zurueck.
	 * 
	 * @param username
	 *            zu suchender Username
	 * @return Account-Objekt das diesem Username entspricht. Falls nicht
	 *         existent <code>null</code>.
	 */
	public Account getAccountByUsername(String username) { // checked
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { tableName }, "benutzername='" + username + "'");
		try {
			if (rs.next()) {
				return new Account(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.Log.getInstance().write("AccountController",
					"Error while reading Account from Database");
		}
		return null;
	}

	/**
	 * Diese Methode selektiert alle Accounts mit uebergebenem Accounttyp.
	 * 
	 * @param accounttype
	 *            Es gibt drei unterschiedliche Accounttypen, um zwischen den
	 *            unterschieldichen Benutzeransichten und Benutzerrechten der
	 *            Accounttypen zu differenzieren.
	 * @return Es wird ein Vector zurueckgegeben, welcher alle Account-Objekte
	 *         enthaelt und zwar alle Account-Objekte mit uebergebenem
	 *         Accounttyp.
	 */
	public Vector<Account> getAccountsByAccounttype(int accounttype) { // checked

		Vector<Account> accountvec = new Vector<Account>(30, 10);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "accounttyp = " + accounttype;

		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Account currentacc;
				currentacc = new Account(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));

				accountvec.add(currentacc);
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountvec;

	}

	/**
	 * Diese Methode selektiert alle Verwalter Accounts mit uebergebenem
	 * Institut.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Es wird ein Vector zurueckgegeben, welcher alle Account-Objekte
	 *         enthaelt und zwar alle Account-Objekte mit uebergebenem Institut
	 *         und Accounttyp 2 (Verwalter).
	 */
	public Vector<Account> getClerkAccountsByInstitute(int id) { // checked

		Vector<Account> accountvec = new Vector<Account>(30, 10);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "institut = " + id + " and accounttyp = 2";

		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Account currentacc;
				currentacc = new Account(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));

				accountvec.add(currentacc);
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountvec;

	}

	/**
	 * Diese Methode selektiert alle Accounts mit uebergebenem Institut.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Es wird ein Vector zurueckgegeben, welcher alle Account-Objekte
	 *         enthaelt und zwar alle Account-Objekte mit uebergebenem Institut.
	 */
	public Vector<Account> getAccountsByInstitute(int id) { // checked

		Vector<Account> accountvec = new Vector<Account>(30, 10);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "institut = " + id;

		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Account currentacc;
				currentacc = new Account(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));

				accountvec.add(currentacc);
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountvec;

	}

	/**
	 * Diese Methode selektiert alle Provider Accounts mit uebergebenem
	 * Institut.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Es wird ein Vector zurueckgegeben, welcher alle Account-Objekte
	 *         enthaelt und zwar alle Account-Objekte mit uebergebenem Institut.
	 */
	public Vector<Account> getProviderAccountsByInstitute(int id) { // checked

		Vector<Account> accountvec = new Vector<Account>(30, 10);

		String[] select = { "*" };
		String[] from = { tableName };
		String where = "institut = " + id + " and accounttyp = 1";

		ResultSet rs = dbc.select(select, from, where);
		try {
			while (rs.next()) {
				Account currentacc;
				currentacc = new Account(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));

				accountvec.add(currentacc);
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountvec;

	}

	/**
	 * Die Methode gibt den Namen des Stellvertreters zurueck. Wen kein Name
	 * vorhanden ist, wird ein <code>NULL</code> zurueck gegeben.
	 * 
	 * @param name
	 *            Name des Accounts.
	 * @return Gibt den Namen des Stellvertreters zurueck.
	 */
	public String getRepresentative(String name) { // checked

		ResultSet rs = dbc.select(new String[] { "stellvertreter" },
				new String[] { "accounts" }, "benutzername = '" + name + "'");
		try {
			if (rs.next()) {
				return rs.getString(1);
			} else
				return null;
		} catch (SQLException e) {
			logger.Log.getInstance().write("AccountController",
					"Error while reading Account from Database");
		}
		return null;
	}

	/**
	 * Gibt den Account des Stellvertreters zurueck. Gegeben wird der EINGENE
	 * Bentuzername!
	 * 
	 * @param userName
	 *            Der Benutzername des Accounts, fuer den wir delegierte
	 *            Angebote suchen.
	 * @return Den Account des Stellvertreters.
	 */
	public Account getRepresentativeAccount(String userName) {
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { "Accounts" }, "stellvertreter= '" + userName
						+ "'");
		try {
			if (rs.next()) {
				return new Account(rs.getString("benutzername"),
						rs.getString("passworthash"), rs.getInt("accounttyp"),
						rs.getString("email"), rs.getString("name"),
						rs.getInt("institut"), rs.getString("stellvertreter"));
			} else
				return null;
		} catch (SQLException e) {
			logger.Log.getInstance().write(
					"AccountController",
					"Error while reading Account from Database for <"
							+ userName + ">.");
		}
		return null;
	}

	/**
	 * Die Methode setzt einen Stellvertreter fuer den Account.
	 * 
	 * @param name
	 *            Der Name des Stellvertreters.
	 */

	public void setRepresentative(String name, String newrepresentative) { // checked
		String[] columns = { "stellvertreter" };
		Object[] values = { newrepresentative };
		String where = "benutzername = '" + name + "'";

		dbc.update("accounts", columns, values, where);
	}

	/**
	 * Gibt die Namen aller User zurueck die von diesem User vertreten werden
	 * 
	 * @param username
	 *            Eigener Nutzername
	 * @return Namen aller vertrenenen User
	 */
	public Vector<String> represents(String username) {
		Vector<String> ret = new Vector<String>();
		ResultSet rs = dbc
				.select(new String[] { "benutzername" },
						new String[] { tableName }, "stellvertreter='"
								+ username + "'");
		try {
			while (rs.next())
				ret.add(rs.getString(1));
		} catch (SQLException e) {
			Helper.log.write("AccountController",
					"Error getting representing names");
		}
		return ret;
	}

}
