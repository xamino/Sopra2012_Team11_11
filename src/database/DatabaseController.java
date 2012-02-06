package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import database.account.Account;

/**
 * Verwaltet saemtliche beziehungen zwischen der Datenbank und dem System.
 *
 */
public class DatabaseController {

	// Store singleton instance of database here:
	/**
	 * Datenbank instanz
	 */
	private static DatabaseController instance;
	/**
	 * Verbingung zur Datenbank
	 */
	private Connection con;
	/**
	 * Statement zum ausfuerhen von SQL Befehlen
	 */
	private Statement st;
	/**
	 * Username fuer den Datenbankzugriff
	 */
	private String user;
	/**
	 * Passwort fuer den Datenbankzugriff
	 */
	private String password;
	/**
	 * Datenbankname
	 */
	private String db;

	/**
	 * Vector mit den Accounts der Datenbank.
	 */
	private Vector<Account> accounts = new Vector<Account>();

	/**
	 * den Account-Vektor mit Datenbankeintraegen fuellen. 
	 * Zur besseren Performence wird nur mit einem Vektor gearbeitet und nicht 
	 * bei jeder Aenderung sofort eine Verbindung mit der Datenbank aufgenommen.
	 */
	private void loadAccountList() {
	
		}


	/**
	 * Gibt den Vector zurueck der die Accounts enthaelt.
	 * 
	 * @return Gibt den Accounts Vector zurueck.
	 */
	public Vector<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Fuegt einen Account der Datenbank hinzu. 
	 * Dabei wird darauf geachtet, dass die ID (Benutzername) nicht doppelt vorkommt.
	 * Zudem wir hier das gegebene Passwort in einen Hash-Wert umgewandelt.
	 * 
	 * @param acc
	 *            Account-Objekt des zu erstellenden Accounts. Im Feld
	 *            Passworthash muss das Klartextpasswort stehen, da erst hier der
	 *            zum Passwort gehoerige Hash-Wert erstellt wird.
	 * @return <code>true</code> wenn ein Account erstellt werden kann,
	 *         <code>false</code> ansonsten.
	 *         
	 * @see LoginHelper#hashCode()
	 */
	public boolean addAccount(Account acc) {
		return true;
	}

	/**
	 * Loescht einen Account aus der Datenbank. 
	 * Entfernt den Eintrag mit der ID (benutzername).
	 * 
	 * @param benutzername
	 *            Benutzername des zu loeschenden Accounts
	 */

	public void delAccount(String benutzername) {
	}

	
	/**
	 * Holt einen bestimmten Account aus der Datenbank.
	 * Falls kein Account mit dem Benutzernamen exestiert, wird ein <code>NULL</code> zurueck gegeben.
	 * @param benutzername
	 *            Benutzername des zu holenden Accounts
	 * @return Gibt das Account-Obejkt zurueck, falls vorhanden, ansonsten bekommt man ein <code>Null</code>.
	 */

	public Account getAccount(String benutzername) {
		return null;
	}

	/**
	 * Liest die Informationen zum Verbindungsaufbau zur Datenbank aus einer Config-Datei.
	 * Die Datei behinhaltet den Username,den Datenbanknamen und das Passwort welche benoetigt werden um eine Verbindung 
	 * zur Datenbank herzustellen.
	 * 
	 */
	private void getLoginInfo() {
	}

	/**
	 * Erstellt eine Datei mit Logininformationen für die Datenbank.
	 * In der Datei werden der Usename,der Datenbankname und das Passwort gespeichert. Dies wird benoetigt damit 
	 * eine Verbindung mit der Datenbank unabhaengig der Hardware hergestellt werden kann.
	 * 
	 */
	private void createLoginInfo() {

	}

	/**
	 * Gibt die Instanz der Datenbank zurueck. Falls noch keine existiert wird an dieser Stelle eine neue Instanz erzeugt.
	 * 
	 * @return Instanz der Datenbank.
	 */
	public static DatabaseController getInstance() {
		return instance;
	}

	/**
	 * Privater Konstruktor da nur eine Datenbankinstanz erstellt werden soll.
	 * Die Logininformationen werden aus der Config-Datei geladen. Die Config-Datei wird in der <code>createLoginInfo()</code> Methode erstellt. 
	 */
	private DatabaseController() {
		
	}
}
