package database;

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
	 * Method for getting a valid reference of this object.
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

	}

	/**
	 * Methode welche ein SQL "update" Statement ausführt.
	 */
	synchronized public void update() {

	}

	/**
	 * Methode welche ein SQL "delete" Statement ausführt.
	 */
	synchronized public void delete() {

	}

	/**
	 * Methode welche ein SQL "insert" Statement ausführt.
	 */
	synchronized public void insert() {

	}

	/**
	 * Methode welche ein SQL "select" Statement ausführt.
	 */
	synchronized public void select() {

	}

	/**
	 * Methode welche ein SQL "insert on not null update" Statement ausführt.
	 */
	synchronized public void insertOnNullElseUpdate() {

	}
}
