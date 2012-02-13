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
	 * Diese Methode entfernt alle erstellten Dateien die zum Download zur
	 * Verfuegung gestellt wurden.
	 * 
	 * @param sessionId
	 *            Parameter "sessionId" identifiziert die zu loeschenden
	 *            Download-Dateien. Dabei besteht die sessionId aus einem Teil
	 *            des Session-Namens und einem zufaellig generierten Teil.
	 */
	private static void deleteAllFilesBySession(String sessionId) {

	}

	/**
	 * Diese Methode erstellt eine Download-Datei bei uebergebener Session-Id
	 * und der jeweiligen Download-Datei.
	 * 
	 * @param sessionId
	 *            Parameter "sessionId" identifiziert die zu loeschenden
	 *            Download-Dateien. Dabei besteht die sessionId aus einem Teil
	 *            des Session-Namens und einem zufaellig generierten Teil.
	 * @param file
	 *            Parameter "file" ist die jeweilige Download-Datei, die zum
	 *            Donwload verfuegbar sein soll.
	 * @return Zurueckgegeben wird der Pfad des erstellten Download-Datei.
	 */
	private static String createFile(String sessionId, File file) {
		return null;

	}

}
