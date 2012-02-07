/**
 * @author Patryk Boczon 
 * @author Oemer Sahin
 **/
package database.account;

/**
 * Verwaltet alle Datenbankzugriffe auf Account-bezogene Daten
 */
import java.util.Vector;

import database.DatabaseController;

/**
 * Der Accountcontroller behandelt die Verwaltungsmethoden von allen Accounts.
 * 
 */

public class AccountController {
	
	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private AccountController(){
		
	}
	
	/**
	 * Diese Methode gibt den Namen eines Instituts bei uebergebener Id.
	 * @param id
	 * Id ist der Primaerschluessel in der Institute-DB.
	 * @return
	 * Zurueckgegeben wird der Name des Institutes.
	 */
	public String getInstituteById(int id){
		return null;
	}
	
	/**
	 * Diese Instanz
	 */
	public DatabaseController dbc;

	/**
	 * Diese Methode erstellt einen neuen Account mit uebergebenem Account Parameter.
	 * 
	 * @param account
	 * Parameter ist ein Account-Objekt, der alle noetigen Attribute enthaelt. 
	 */
	public void createAccount(Account account) {

	}

	/**
	 * Diese Methode gibt falls vorhanden die Instanz des Account-Objekts zurueck. Andernfalls wird eine Instanz angelegt.
	 * @return
	 */
	public Account getInstance() {
		return null;

	}
	/**
	 * Diese Methode loescht den gewuenschten Account mit uebergebenem Account-Objekt.
	 * @param account
	 * Parameter ist ein Account-Objekt, der alle noetigen Attribute enthaelt.
	 */
	public void deleteAccount(Account account) {

	}
	/**
	 * Diese Methode nimmt Aenderungen an einem Account-Objekt vor. Dabei werden die geaenderten Attribute vom uebergebenem Parameter uebernommen.
	 * @param account
	 * Parameter ist ein Account-Objekt, der alle noetigen Attribute enthaelt.
	 */
	public void updateAccount(Account account) {

	}

	/**
	 * Diese Methode selektiert alle Accounts mit uebergebenem Accounttyp.
	 * @param accounttype
	 * Es gibt drei unterschiedliche Accounttypen, um zwischen den unterschieldichen Benutzeransichten und Benutzerrechten der Accounttypen zu differenzieren.
	 * @return
	 * Es wird ein Vector zurueckgegeben der alle Account-Objekte enthaelt und zwar alle Account-Objekte mit uebergebenem Accounttyp.
	 */
	public Vector<Account> getAccountsByAccounttype(int accounttype) {
		return null;

	}

	/**
	 * Diese Methode selektiert alle Accounts mit uebergebenem Institut.
	 * @param institute
	 * @return
	 * Es wird ein Vector zurueckgegeben der alle Account-Objekte enthaelt und zwar alle Account-Objekte mit uebergebenem Institut.
	 */
	public Vector<Account> getAccountsByInstitute(int institute) {
		return null;

	}

}
