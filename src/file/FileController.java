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
	 * Diese Methode erstellt eine Download-Datei mit angegebenen Nutzernamen
	 * und der jeweiligen Download-Datei. Sollte bereits eine Datei existieren wird diese ueberschrieben.
	 * 
	 * @param username
	 *            Parameter "username" identifiziert die zu loeschenden
	 *            Download-Dateien. 
	 * @param file
	 *            Parameter "file" ist die jeweilige Download-Datei, die zum
	 *            Donwload verfuegbar sein soll.
	 * @return Zurueckgegeben wird der Pfad des erstellten Download-Datei.
	 */
	public static String createFile(String username, File file) {
		return null;

	}

}
