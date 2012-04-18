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
import java.util.Vector;

import database.DatabaseController;

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
	
	final static String tableName = "Accounts";//tabellenname

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
	public boolean createAccount(Account account) {

		Object[] values = { account.getUsername(), account.getPasswordhash(),
				account.getAccounttype(), account.getEmail(),
				account.getName(), account.getInstitute(),
				account.getRepresentative() };

		return dbc.insert(tableName, values);
	}

	/**
	 * Diese Methode loescht den gewuenschten Account mit uebergebenem
	 * Account-Objekt.
	 * 
	 * @param account
	 *            Parameter "account" ist ein Account-Objekt, welches alle
	 *            noetigen Attribute enthaelt.
	 */
	public void deleteAccount(Account account) {

		String where = "benutzername = '" + account.getUsername() + "'";

		dbc.delete("tableName", where);
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
	public boolean updateAccount(Account account) {

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
	public Vector<Account> getAllAccounts() {
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
	public Account getAccountByUsername(String username) {
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { tableName }, "benutzername='" + username + "'");
		try {
			if (rs.next())
				return new Account(rs.getString(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));
			else
				return null;
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
	public Vector<Account> getAccountsByAccounttype(int accounttype) {

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
	 * Diese Methode selektiert alle Accounts mit uebergebenem Institut.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Es wird ein Vector zurueckgegeben, welcher alle Account-Objekte
	 *         enthaelt und zwar alle Account-Objekte mit uebergebenem Institut.
	 */
	public Vector<Account> getAccountsByInstitute(int id) {

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
	 * Die Methode gibt den Namen des Stellvertreters zurueck. Wen kein Name
	 * vorhanden ist, wird ein <code>NULL</code> zurueck gegeben.
	 * 
	 * @param name
	 *            Name des Stellvertreters.
	 * @return Gibt den Namen des Stellvertreters zurueck.
	 */
	public String getRepresentative(String name) {

		return null;
	}

	/**
	 * Die Methode setzt einen Stellvertreter fuer den Account.
	 * 
	 * @param name
	 *            Der Name des Stellvertreters.
	 */

	public void setRepresentative(String name) {

	}

}
