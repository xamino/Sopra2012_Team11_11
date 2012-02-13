/**
 * @author Patryk Boczon 
 * @author Oemer Sahin
 **/
package database.account;

/**
 * Verwaltet alle Datenbankzugriffe auf Account-bezogene Daten.
 */
import java.util.Vector;

import database.DatabaseController;
import database.application.ApplicationController;

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

	}

	/**
	 * Diese Methode gibt den Namen eines Instituts bei uebergebener Id.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Zurueckgegeben wird der Name des Institutes.
	 */
	public String getInstituteById(int id) {
		return null;
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
	 */
	public void createAccount(Account account) {

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

	}

	/**
	 * Diese Methode nimmt Aenderungen an einem Account-Objekt vor. Dabei werden
	 * die geaenderten Attribute vom uebergebenem Parameter uebernommen.
	 * 
	 * @param account
	 *            Parameter "account" ist ein Account-Objekt, welches alle
	 *            noetigen Attribute enthaelt.
	 */
	public void updateAccount(Account account) {

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
		return null;

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
		return null;

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
