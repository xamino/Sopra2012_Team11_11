package file;

import userManagement.UserData;

/**
 * Klasse zur erstellung von Exceldateien mit den getaetigten Einstellungen
 * eines Verwalters.
 * 
 * @author Manuel Guentzel
 * 
 */
public abstract class ExcelExport {
	/**
	 * Erstellt eine neue Excel Datei mit den getaetigten Einstellungen des
	 * Verwalters und gibt den Link dazu zurueck. Dem uebergebenen
	 * UserData-Objekt werden der Benutzername und die Session entnommen um
	 * diese dann mittels dem FileController zum Download zur verfügung zu
	 * stellen.
	 * 
	 * @param data
	 *            UserData Objekt des Benutzers.
	 */
	public static String export(final UserData data) {
		return null;

	}

}
