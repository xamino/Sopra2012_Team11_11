package database.institute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseController;

public class InstituteController {

	/**
	 * Variable für die Instanz der Klasse.
	 */
	private static InstituteController instController;

	/**
	 * Gibt eine Instanz des InstituteContoller zurück.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static InstituteController getInstance() {
		if (instController == null)
			instController = new InstituteController();
		return instController;

	}

	private InstituteController() {
		dbc = DatabaseController.getInstance();
		logger.Log.getInstance().write("InstituteController",
				"Instance created.");
	}

	/**
	 * Diese Instanz dient zum Zugang in die Datenbank.
	 */
	public DatabaseController dbc;

	/**
	 * Diese Methode gibt den Namen eines Instituts bei uebergebener Id.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Zurueckgegeben wird der Name des Institutes.
	 */
	public String getInstituteNameById(int id) {
		ResultSet rs = dbc.select(new String[] { "Name" },
				new String[] { "institute" }, "IID=" + id);
		try {
			if (rs.next()) {
				return rs.getString("Name");
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets all institutes in the database.
	 * 
	 * @return A vector with all the institutes.
	 */
	public Vector<Institute> getAllInstitutes() {
		ResultSet rs = dbc.select(new String[] { "*" },
				new String[] { "institute" }, null);
		Vector<Institute> inst = new Vector<Institute>();
		try {
			while (rs.next()) {
				inst.add(new Institute(rs.getInt("IID"), rs.getString("Name")));
			}
			return inst;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}