/**
 * @author Manuel Güntzel
 * @author Tamino Hartmann
 */

// TODO: Is sql.date format handled correctly throughout?

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import config.Configurator;

import logger.Log;

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
	 * Variable welche auf den Logger zeigt.
	 */
	private Log log;

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
	private String database;
	/**
	 * Portnummer
	 */
	private String port;

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
		// Get & and save logger:
		this.log = Log.getInstance();
		log.write("DatabaseController", "Instance created.");
		// Connect to database:
		try {
			log.write("DatabaseController", "Connecting to Database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// getLoginInfo();
			Configurator conf = Configurator.getInstance();
			user = conf.getString("user");
			password = conf.getString("password");
			database = conf.getString("database");
			port = Integer.toString(conf.getInt("port"));

			if (password != null) {
				log.write("DatabaseController", "Try login: "
						+ "jdbc:mysql://localhost:" + port + "/" + database
						+ "?user=" + user + "&password=" + password);
				con = DriverManager.getConnection("jdbc:mysql://localhost:"
						+ port + "/" + database + "?user=" + user
						+ "&password=" + password);
			} else {
				log.write("DatabaseController", "Try login: "
						+ "jdbc:mysql://localhost:" + port + "/" + database
						+ "?user=" + user);
				con = DriverManager.getConnection("jdbc:mysql://localhost:"
						+ port + "/" + database + "?user=" + user);
			}
			st = con.createStatement();

			log.write("DatabaseController", "Connection successful.");
		} catch (Exception e) {
			log.write(
					"DatabaseController",
					"Error while connecting to database: please check if DB is running and if logindata is correct (~/.sopraconf)");
			// Commented out by Tamino (it was making me edgy... :D )
			// e.printStackTrace();
		}
	}

	/**
	 * Methode welche ein SQL "update" Statement ausfuehrt.
	 * 
	 * @param table
	 *            Name der Tabelle.
	 * @param columns
	 *            Name der Spalten welche aktualisiert werden sollen.
	 * @param values
	 *            Daten, welche in die entsprechenden Spalten gefühlt werden
	 *            sollen.
	 * @param where
	 *            Bedingung für die Aktualisierung.
	 * @return <code>True</code> wenn erfolgreich, sonst <code>false</code>.
	 */
	synchronized public boolean update(String table, String[] columns,
			Object[] values, String where) {
		String update = "UPDATE " + table + " SET "
				+ commanator(columns, values) + " WHERE " + where;
		try {
			st.executeUpdate(update);
			return true;
		} catch (SQLException e) {
			log.write("DatabaseController", "UPDATE error! <" + update + ">");
			return false;
		}
	}

	/**
	 * Methode welche ein SQL "delete" Statement ausfuehrt.
	 * 
	 * @param table
	 * 
	 *            Tabelle von der geloescht werden soll.
	 * @param where
	 *            Where Bedingung.
	 * @return boolean Ob die Aktion fehlerfrei geklappt hat.
	 */
	synchronized public boolean delete(String table, String where) {

		String del = "DELETE FROM " + table + " WHERE " + where;
		try {
			st.executeUpdate(del);
			return true;
		} catch (SQLException e) {
			log.write("DatabaseController", "DELETE error! <" + del + ">");
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Methode welche ein SQL "insert" Statement ausfuehrt.
	 * 
	 * @param table
	 *            Name der Tabelle.
	 * @param values
	 *            Einzufügende Werte.
	 * @return Boolean welcher angibt, ob INSERT erfolgreich war.
	 */
	synchronized public boolean insert(String table, Object[] values) {
		String insert = "INSERT INTO " + table + " VALUES ("
				+ commanator(values) + ")";
		try {
			st.executeUpdate(insert);
			return true;
		} catch (SQLException e) {
			log.write("DatabaseController", "INSERT error! <" + insert + ">");
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gibt die anzahl der Zeilen einer Tabelle aus die die Where Bedingung
	 * erfuellen
	 * 
	 * @param from
	 *            Tabellen die in die Suche miteinbezogen werden sollen
	 * @param where
	 *            zu erfuellende Bedinung
	 * @return Anzahl der Zeilen
	 */
	synchronized public int count(String[] from, String where) {
		String sel = "SELECT COUNT(*) FROM " + commanator(from);
		if (where != null)
			sel += " WHERE " + where;
		ResultSet rs;
		try {
			rs = st.executeQuery(sel);
			rs.next();
			return rs.getInt("COUNT(*)");
		} catch (SQLException e) {
			log.write("DatabaseController", "COUNT error! <" + sel + ">");
		}
		return 0;
	}

	/**
	 * Methode welche ein SQL "select" Statement ausfuehrt.
	 * 
	 * @param select
	 *            Welche Werte ausgewählt werden sollen.
	 * @param from
	 *            Namen der Tabellen.
	 * @param where
	 *            Zusätzliche Bedingung. Wird keine benötigt kann
	 *            <code>null</code> gesetzt werden.
	 * @return Gibt ein <code>ResultSet</code> mit den Antworddaten zurück.
	 */
	synchronized public ResultSet select(String[] select, String[] from,
			String where) {
		String sel = "SELECT " + commanator(select) + " FROM "
				+ commanator(from);
		if (where != null)
			sel += " WHERE " + where;
		ResultSet rs;
		try {
			rs = st.executeQuery(sel);
			return rs;
		} catch (SQLException e) {
			log.write("DatabaseController", "SELECT error! <" + sel + ">");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Methode welche ein SQL "insert on not null update" Statement ausfuehrt.
	 * 
	 * @param table
	 *            Name der Tabelle.
	 * @param columns
	 *            Namen der Spalten.
	 * @param values
	 *            Ensprechende Werte welche eingefügt oder aktualiesiert werden
	 *            sollen.
	 * @return <code>True</code> wenn erfolgreich, ansonsten <code>false</code>.
	 */
	synchronized public boolean insertOnNullElseUpdate(String table,
			String[] columns, Object[] values) {
		String update = "INSERT INTO " + table + " VALUES ("
				+ commanator(values) + ") ON DUPLICATE KEY UPDATE "
				+ commanator(columns, values);
		try {
			st.executeUpdate(update);
			return true;
		} catch (SQLException e) {
			log.write("DatabaseController", "INSERTONNULLUPDATE error! <"
					+ update + ">");
		}
		return false;
	}

	// /**
	// * Erstellt eine Datei mit Logininformationen für die Datenbank. In der
	// * Datei werden der Usename,der Datenbankname und das Passwort
	// gespeichert.
	// * Dies wird benoetigt damit eine Verbindung mit der Datenbank unabhaengig
	// * der Hardware hergestellt werden kann.
	// *
	// */
	// private void createLoginInfo() {
	// String sep = System.getProperty("file.separator");
	// String home = System.getProperty("user.home");
	// File f = new File(home + sep + ".sopraconf");
	// f.delete(); // erst vorhandene löschen
	// try {
	// f.createNewFile(); // dann neue erstellen
	// } catch (IOException e) {
	// System.out
	// .println("[Database] createLoginInfo():f.createNewFile()");
	// e.printStackTrace();
	// }
	// try {
	// BufferedWriter buf = new BufferedWriter(new FileWriter(f));
	// buf.write("database=sopra");
	// buf.newLine();
	// buf.write("port=3306");
	// buf.newLine();
	// buf.write("user=root");
	// buf.close();
	// } catch (IOException e) {
	// System.out.println("[Database] createLoginInfo():BufferedWriter");
	// e.printStackTrace();
	// }
	// log.write("DatabaseController", "New loginfile created.");
	// }
	//
	// /**
	// * Liest die Informationen zum Verbindungsaufbau zur Datenbank aus einer
	// * Config-Datei. Die Datei behinhaltet den Username,den Datenbanknamen und
	// * das Passwort welche benoetigt werden um eine Verbindung zur Datenbank
	// * herzustellen.
	// *
	// */
	// private void getLoginInfo() { // Liest die Config Datei aus
	// String sep = System.getProperty("file.separator");
	// String home = System.getProperty("user.home");
	// try {
	// File f = new File(home + sep + ".sopraconf"); // datei im homeordner
	// // namens .sopraconf
	// if (f.exists()) { // falls config datei existiert
	// BufferedReader buf = new BufferedReader(new FileReader(f));
	// Field field;
	// database = "sopra";
	// user = "root";
	// port = "3306";
	// password = null;
	// while (true) {
	// if (!buf.ready())
	// break;
	// String conf = buf.readLine();
	// String[] confarr = conf.split("=");
	// if (confarr[0].trim().equalsIgnoreCase("password")
	// || confarr[0].trim().equalsIgnoreCase("user")
	// || confarr[0].trim().equalsIgnoreCase("database")
	// || confarr[0].trim().equalsIgnoreCase("port")) {
	// field = getClass().getDeclaredField(confarr[0].trim());
	// field.set(this, confarr[1].trim());
	// } else
	// log.write("DatabaseController", "Error in ConfigFile on: " + conf);
	// }
	// } else { // falls noch nicht existent
	// createLoginInfo(); // config datei erstellen
	// database = "sopra";
	// user = "root";
	// port = "3306";
	// password = null;
	// }
	// log.write("Database", "Try Login: user=" + user
	// + " password=" + password + " database=" + database
	// + " port=" + port);
	// } catch (Exception e) {
	// System.out.print("[Database] getLoginInfo()");
	// e.printStackTrace();
	//
	// }
	// }

	/**
	 * Hilfsmethode zum Konkatenieren von Strings mit Kommasetzung.
	 * 
	 * @param stringz
	 *            Zu konkatenierende Strings.
	 * @return Konkatenierter String.
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
	 * Hilfsmethode zum Konkatenieren von Objekten mit Kommasetzung. Dabei
	 * werden Strings mit einfachen Anfuehrungszeichen eingeklammert.
	 * 
	 * @param objects
	 *            Zu konkatenierende objekte.
	 * @return Konkatenierter String.
	 */
	private String commanator(Object[] objects) {
		String ret = "";
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof String)
				// Correctly display strings:
				ret += "'" + objects[i] + "'";
			else if (objects[i] instanceof Date) {
				// Correctly parse & enter dates:
				ret += "'" + new java.sql.Date(((Date) objects[i]).getTime())
						+ "'";
			} else
				ret += objects[i];
			if (i != (objects.length - 1))
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
