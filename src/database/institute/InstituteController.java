package database.institute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import servlet.Helper;

import database.DatabaseController;
import database.account.Account;
import database.account.AccountController;
import database.offer.Offer;
import database.offer.OfferController;

public class InstituteController {

	/**
	 * Variable für die Instanz der Klasse.
	 */
	private static InstituteController instController;
	/**
	 * AccountController Instanz um beim Loeschen die Institute der Account
	 * Tabelle zu updaten
	 */
	private static AccountController acccon;

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
		db = DatabaseController.getInstance();
		acccon = AccountController.getInstance();
		logger.Log.getInstance().write("InstituteController",
				"Instance created.");
	}

	/**
	 * Diese Instanz dient zum Zugang in die Datenbank.
	 */
	public DatabaseController db;

	final static String tableName = "Institute";// tabellenname

	/**
	 * Diese Methode gibt den Namen eines Instituts bei uebergebener Id.
	 * 
	 * @param id
	 *            Id ist der Primaerschluessel in der Institute-DB.
	 * @return Zurueckgegeben wird der Name des Institutes.
	 */
	public String getInstituteNameById(int id) {
		ResultSet rs = db.select(new String[] { "Name" },
				new String[] { tableName }, "IID=" + id);
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
		ResultSet rs = db.select(new String[] { "*" },
				new String[] { tableName }, null);
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

	/**
	 * Inserts an institute in the database.
	 * 
	 * @param institute
	 *            The institute object to insert.
	 * @return A boolean flag showing if the action was successful.
	 */
	public boolean addInstitute(Institute institute) {
		return db.insert(tableName, new Object[] { institute.getIID(),
				institute.getName() });
	}

	/**
	 * Deletes an institute from the database. All accounts with said Institute
	 * will have institute set to 0.
	 * 
	 * @param institute
	 *            The institute to delete.
	 * @return A flag whether the operation was successful.
	 */
	public boolean deleteInstitute(Institute institute) {
		// Update all accounts:
		Vector<Account>accs = acccon.getAccountsByInstitute(institute.getIID());
		for(Account a : accs){
			a.setInstitute(0);
			acccon.updateAccount(a);
		}
		// Update all offers:
		Vector<Offer>offers = OfferController.getInstance().getOffersByInstitute(institute.getIID());
		for(Offer offer: offers){
			offer.setInstitute(0);
			OfferController.getInstance().updateOffer(offer);
		}
		return db.delete(tableName, "IID=" + institute.getIID());
	}

	public Institute getInstituteByIID(int IID) {
		ResultSet rs = db.select(new String[] { "*" },
				new String[] { tableName }, "IID=" + IID);
		try {
			if (rs.next()) {
				return new Institute(rs.getInt("IID"), rs.getString("Name"));
			}
			return null;
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gibt alle Institute zurueck die ein Clerk repraesentiert
	 * 
	 * @param username
	 *            Username des Clerks
	 * @return Alle Institutsnummern in einem Vector
	 */
	public Vector<Integer> getAllRepresentingInstitutes(String username) {
		Vector<Integer> ret = new Vector<Integer>();
		ResultSet rs = db.select(new String[] { "DISTINCT IID" }, new String[] {
				tableName, "Accounts" },
				"Institute.IID=Accounts.institut AND (Accounts.benutzername='"
						+ username + "' OR Accounts.stellvertreter='"
						+ username + "')");
		try {
			while (rs.next()) {
				ret.add(rs.getInt(1));
			}
		} catch (Exception e) {
			Helper.log.write("InstituteController",
					"Error getting representing Institutes");
		}
		return ret;
	}

	/**
	 * Generate a new ID.
	 * 
	 * @return generated ID
	 */
	public int getNewInstID(String tablename) {
		int newID = 0;
		boolean check = false;
		while (!check) {
			newID = generateRandomNr(1, 9999);
			Object[] data = { newID, "" };
			check = db.insert(tablename, data);
		}
		db.delete(tablename, "IID= " + newID);
		return newID;
	}

	/**
	 * Generate a random number.
	 * 
	 * @param aStart
	 *            Start of the number.
	 * @param aEnd
	 *            End of the number.
	 * @return generated number.
	 */
	private int generateRandomNr(int aStart, int aEnd) {

		Random random = new Random();
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}
}