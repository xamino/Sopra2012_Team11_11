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
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Verbindung zur Datenbank mit allen Modifikationsbefehlen.
 * 
 */
public class DatabaseController {

	/**
	 * Variable for storing the instance of the class.
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
	 * Method for getting a valid reference of this object.
	 * 
	 * @return Instance of DatabaseController.
	 */
	public static DatabaseController getInstance() {
		if (instance == null)
			instance = new DatabaseController();
		return instance;
	}

	/**
	 * Private constructor for DatabaseController for implementing the singleton
	 * instance. Use getInstance() to get a reference to an object of this type.
	 */
	private DatabaseController() {
		try {
			System.out.println("Database: Connecting to Database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			getLoginInfo();
			if (password != null)
				con = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/sopra?user="
								+ user + "&password=" + password);
			else
				con = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/" + db
								+ "?user=" + user);
			st = con.createStatement();

			System.out.println("Database: Connection successful.");
		} catch (Exception e) {
			System.out
					.println("Database: Error while connecting to database: please check if DB is runnung and if logindata is correct (~/.sopraconf)");
			// Commented out by Tamino (it was making me edgy... :D )
			// e.printStackTrace();
		}
	}

	/**
	 * Methode welche ein SQL "update" Statement ausfuehrt.
	 */
	synchronized public boolean update(String table, String[] columns,
			Object[] values, String where) {
		String update = "UPDATE " + table + " SET "
				+ commanator(columns, values) + " WHERE " + where;
		ResultSet rs;
		try {
			rs = st.executeQuery(update);
			return true;
		} catch (SQLException e) {
			System.out.println("[DatabaseController] Error: " + update);
		}
		return false;
	}

	/**
	 * Methode welche ein SQL "delete" Statement ausfuehrt.
	 * @param table Tabelle von der geloescht werden soll
	 * @param where	Where Bedingung
	 */
	synchronized public void delete(String table, String where) {
		String del = "DELETE FROM "+table+" WHERE "+where;
		try {
			st.executeUpdate(del);
		} catch (SQLException e) {
			System.out.println("[DatabaseController] Deletion failed!");
		}
	}

	/**
	 * Methode welche ein SQL "insert" Statement ausfuehrt.
	 */
	synchronized public void insert() {

	}

	/**
	 * Methode welche ein SQL "select" Statement ausfuehrt.
	 * 
	 * @param select
	 *            Welche spalten ausgewaehlt werden sollen
	 * @param from
	 *            Aus welchen Tabellen ausgewaehlt wird
	 * @param where
	 *            Die zu erfuellende Bedingung
	 * @return ResultSet der Anfrage
	 */
	synchronized public ResultSet select(String[] select, String[] from,
			String where) {
		String sel = "SELECT " + commanator(select) + " FROM "
				+ commanator(from) + " WHERE " + where;
		ResultSet rs;
		try {
			rs = st.executeQuery(sel);
			return rs;
		} catch (SQLException e) {
			System.out.println("[DatabaseController] Error: " + sel);
		}
		return null;
	}

	/**
	 * Methode welche ein SQL "insert on not null update" Statement ausfuehrt.
	 */
	synchronized public void insertOnNullElseUpdate() {

	}

	/**
	 * Erstellt eine Datei mit Logininformationen für die Datenbank. In der
	 * Datei werden der Usename,der Datenbankname und das Passwort gespeichert.
	 * Dies wird benoetigt damit eine Verbindung mit der Datenbank unabhaengig
	 * der Hardware hergestellt werden kann.
	 * 
	 */
	private void createLoginInfo() {
		String sep = System.getProperty("file.separator");
		String home = System.getProperty("user.home");
		File f = new File(home + sep + ".sopraconf");
		f.delete(); // erst vorhandene löschen
		try {
			f.createNewFile(); // dann neue erstellen
		} catch (IOException e) {
			System.out.println("Database: createLoginInfo():f.createNewFile()");
			e.printStackTrace();
		}
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(f));
			buf.write("sopra");
			buf.newLine();
			buf.write("root");
			buf.close();
		} catch (IOException e) {
			System.out.println("Database: createLoginInfo():BufferedWriter");
			e.printStackTrace();
		}
		System.out.println("Database: New loginfile created!");

	}

	/**
	 * Liest die Informationen zum Verbindungsaufbau zur Datenbank aus einer
	 * Config-Datei. Die Datei behinhaltet den Username,den Datenbanknamen und
	 * das Passwort welche benoetigt werden um eine Verbindung zur Datenbank
	 * herzustellen.
	 * 
	 */
	private void getLoginInfo() { // Liest die Config Datei aus
		String sep = System.getProperty("file.separator");
		String home = System.getProperty("user.home");
		try {
			File f = new File(home + sep + ".sopraconf"); // datei im homeordner
															// namens .sopraconf
			if (f.exists()) { // falls config datei existiert
				BufferedReader buf = new BufferedReader(new FileReader(f));
				db = buf.readLine();
				user = buf.readLine();
				password = buf.readLine();
				buf.close();
				if (password != null)
					System.out.println("Database: Read logininfo: Database='"
							+ db + "' User='" + user + "' Password='"
							+ password + "'");
				else
					System.out.println("Database: Read logininfo: Database='"
							+ db + "' User='" + user + "' without password");
				if (user == null || db == null) { // falls benutzername oder db
													// null neue logininfo
													// erstellen und neu laden
					createLoginInfo();
					db = "sopra";
					user = "root";
					password = null;
				}
			} else { // falls noch nicht existent
				createLoginInfo(); // config datei erstellen
				db = "sopra";
				user = "root";
				password = null;
			}

		} catch (Exception e) {
			System.out.print("Database: getLoginInfo()");
			e.printStackTrace();

		}
	}

	/**
	 * Hilfsmethode zum Konkatenieren von Strings mit Kommasetzung.
	 * 
	 * @param stringz
	 *            zu konkatenierende Strings
	 * @return Konkatenierter String
	 */
	private String commanator(String[] stringz) {
		String ret = "";
		for (int i = 0; i < stringz.length; i++) {
			ret += stringz[i];
			if (i != (stringz.length - 1))
				ret += ", ";
		}
		return ret;
	}

	/**
	 * Hilfsmethode zum konkatenieren von zwei String arrays fuer update
	 * statements. Ergibt in etwa einen String der Form
	 * "name[0]=value[0], name...".
	 * 
	 * @param name
	 *            Namen der Spalten.
	 * @param value
	 *            Werte, welche die Spalten bekommen sollen.
	 * @return String mit zusammengesetzten Inhalt.
	 */
	private String commanator(String[] name, Object[] value) {
		String ret = "";
		if (name.length != value.length)
			return "ERROR IN COMMANATOR!";
		for (int i = 0; i < name.length; i++) {
			ret += name[i] + "=";
			if (value[i] instanceof String)
				ret += "'" + value[i] + "'";
			else
				ret += value[i];
			if (i != (name.length - 1))
				ret += ", ";
		}
		return ret;
	}
}
