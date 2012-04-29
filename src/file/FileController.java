/**
 * @author Oemer Sahin
 */
package file;

import java.io.File;

/**
 * Klasse erstellt und entfernt Dateien zum downloaden zur jeweiligen Session.
 * 
 */

public abstract class FileController {

/**
 * Diese Methode loescht die Export Datei des Angegebenen Users.
 * @param username Username des Users dessen Datei geloescht werden soll
 * @return Wahrheitswert ob der Vorgang erfolgreich war
 */
	public static boolean deleteFile(String username){
		return false;
		
	}

/**
 * Erstellt eine neue beschreibbare Excel Datei.
 * @param username Benutzername des Dateibesitzers
 * @return Datei zum weiterverarbeiten fuer den ExcelExport
 */
	public static File createFile(String username) {
		return null;

	}

}
